package com.owenandroid.keepeye.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.model.MyUser;
import com.owenandroid.keepeye.ui.ExpressActivity;
import com.owenandroid.keepeye.ui.InfoActivity;
import com.owenandroid.keepeye.ui.LoginActivity;
import com.owenandroid.keepeye.ui.PhoneActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/4/8.
 */

public class UserFragment extends Fragment implements View.OnClickListener {
    private Button btnLoginOut;//退出登录
    private TextView tvUserName,tvDesc;//用户名、个人简介
    private CircleImageView cimgIcon;//头像
    private ListView llTool;//功能的listview（查快递和号码归属地）
    private int toolInconsId[] = {R.mipmap.express,R.mipmap.phone};
    private int toolNameId[] = {R.string.check_express,R.string.check_phont};
    private List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        dataList.clear();
        for (int i = 0; i < toolInconsId.length; i++) {
            Map map = new HashMap();
            map.put("iconId",toolInconsId[i]);
            map.put("name",getString(toolNameId[i]));
            dataList.add(map);
        }
    }

    private void initView(View view) {
        btnLoginOut = (Button) view.findViewById(R.id.user_btn_login_out);
        btnLoginOut.setOnClickListener(this);
        tvUserName = (TextView) view.findViewById(R.id.user_tv_username);
        tvDesc = (TextView) view.findViewById(R.id.user_tv_desc);
        cimgIcon = (CircleImageView) view.findViewById(R.id.user_img_icon);
        cimgIcon.setOnClickListener(this);
        llTool = (ListView) view.findViewById(R.id.user_ll_tools);
        llTool.setAdapter(new SimpleAdapter(getActivity(),
                dataList,R.layout.listitem_user_tool,
                new String[]{"iconId","name"},
                new int[]{R.id.tool_list_item_img_icon,R.id.tool_list_item_tv_name}));
        setListViewHeightBasedOnChildren(llTool);
        llTool.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0://查快递
                        startActivity(new Intent(getActivity(),ExpressActivity.class));
                        break;
                    case 1://归属地查询
                        startActivity(new Intent(getActivity(),PhoneActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_btn_login_out:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getString(R.string.login_out));
                builder.setMessage("你确定要退出登录吗？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                });
                builder.show();
                break;
            case R.id.user_img_icon:
                startActivity(new Intent(getActivity(), InfoActivity.class));
                break;
        }
    }
    //解决listView与ScrollView的冲突问题
    private void setListViewHeightBasedOnChildren(ListView listView) {
        SimpleAdapter listAdapter = (SimpleAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
