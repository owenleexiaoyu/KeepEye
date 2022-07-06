package com.owenandroid.keepeye.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.utils.AppConfig;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/4/11.
 */
public class PhoneActivity extends BaseActivity{
    private EditText etPhoneNum;//输入手机号
    private Button btnCheck;//查询按钮
    private TextView tvResult;//显示结果
    private ImageView imgIcon;//根据结果显示公司图片
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        initView();
    }

    private void initView() {
        etPhoneNum = (EditText) findViewById(R.id.phone_et_phonenum);
        btnCheck = (Button) findViewById(R.id.phone_btn_check);
        tvResult = (TextView) findViewById(R.id.phone_tv_result);
        imgIcon = (ImageView) findViewById(R.id.phone_img_comicon);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhoneNum.getText().toString().trim();
                if (!TextUtils.isEmpty(phone)){
                    String url = "http://apis.juhe.cn/mobile/get?phone="
                            + phone + "&key=" + AppConfig.PHONE_KEY;
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(final String t) {
                            super.onSuccess(t);
                            parsaringJson(t);
                        }
                    });
                }else{
                    Toast.makeText(PhoneActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void parsaringJson(String t) {
        try {
            com.owenandroid.keepeye.utils.L.i(this,t);
            JSONObject jsonObject = new JSONObject(t);
            String resultcode = jsonObject.getString("resultcode");
            if ("200".equals(resultcode)){
                //返回成功
                JSONObject obj = jsonObject.getJSONObject("result");
                final String belong = "归属地：" + obj.getString("province") + obj.getString("city");
                final String company = obj.getString("company");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText(belong);
                        switch (company){
                            case "移动":
                                Picasso.get().load(R.mipmap.yidong).into(imgIcon);
                                break;
                            case "联通":
                                Picasso.get().load(R.mipmap.liantong).into(imgIcon);
                                break;
                            case "电信":
                                Picasso.get().load(R.mipmap.dianxin).into(imgIcon);
                                break;
                        }
                    }
                });

            }else{
                Toast.makeText(this, "失败：" + jsonObject.getString("reason"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
