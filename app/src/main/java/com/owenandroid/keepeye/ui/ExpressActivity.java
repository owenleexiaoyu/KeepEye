package com.owenandroid.keepeye.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Spinner;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.adapter.ExpressAdapter;
import com.owenandroid.keepeye.model.ExpressData;
import com.owenandroid.keepeye.utils.AppConfig;
import com.owenandroid.keepeye.utils.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */
public class ExpressActivity extends BaseActivity implements View.OnClickListener {
    private Spinner snCompanyList;

    private ListView expressInfoListView;
    private List<ExpressData> dataList = new ArrayList<>();;
    private ExpressAdapter mAdapter;
    private List<String> comNames;//存放快递公司名称
    private List<String> com_Nums;//存放快递公司代号
    private Button btnCheck;
    private EditText edNumber;
    private String com_no;//查询的快递公司的编号
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);
        initcomData();
        initView();
    }

    private void initView() {
        snCompanyList = (Spinner) findViewById(R.id.exress_sn_company);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,comNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snCompanyList.setAdapter(adapter);
        snCompanyList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){
                    com_no = com_Nums.get(position - 1);
                    Toast.makeText(ExpressActivity.this, "position:"+ position +"快递公司：" + com_no, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ExpressActivity.this, "请选择快递公司", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edNumber = (EditText) findViewById(R.id.express_ed_number);
        btnCheck = (Button) findViewById(R.id.express_btn_check);
        btnCheck.setOnClickListener(this);
        mAdapter = new ExpressAdapter(this,dataList);
        expressInfoListView = (ListView) findViewById(R.id.express_ll_expressinfo_list);
        expressInfoListView.setAdapter(mAdapter);
    }

    private void initcomData() {
        com_Nums = new ArrayList<>();
        comNames = new ArrayList<>();
        comNames.add("选择快递公司");
        //获取快递公司的信息
        String url = "http://v.juhe.cn/exp/com?key=" + AppConfig.EXPRESS_KEY;
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                Toast.makeText(ExpressActivity.this, "查询支持的快递公司成功", Toast.LENGTH_SHORT).show();
                L.e(ExpressActivity.this,t);
                try {
                    JSONObject object = new JSONObject(t);
                    JSONArray array = object.getJSONArray("result");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);

                        //将所有快递公司的名称提取到数据中
                        comNames.add(obj.getString("com"));
                        //将所有快递公司的编号提取到数据中
                        com_Nums.add(obj.getString("no"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                comNames.add("查询支持的快递公司失败");
            }
        });
    }
    @Override
    public void onClick(View v) {
        dataList.clear();
        String num = edNumber.getText().toString();
        if (!TextUtils.isEmpty(num)){
            String url = "http://v.juhe.cn/exp/index?key="+AppConfig.EXPRESS_KEY
                    +"&com="+com_no
                    +"&no="+num;
            RxVolley.get(url, new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    super.onSuccess(t);
                    Toast.makeText(ExpressActivity.this, "查询物流成功", Toast.LENGTH_SHORT).show();
                    L.e(ExpressActivity.this,t);
                    try {
                        JSONObject object = new JSONObject(t);
                        JSONObject jsonObject = object.getJSONObject("result");
                        JSONArray array = jsonObject.getJSONArray("list");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            ExpressData data = new ExpressData(obj.getString("zone"),
                                    obj.getString("remark"),obj.getString("datetime"));
                            dataList.add(data);
                        }
                        Collections.reverse(dataList);
                        mAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
