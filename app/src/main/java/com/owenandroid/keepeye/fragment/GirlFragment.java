package com.owenandroid.keepeye.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.adapter.GirlAdapter;
import com.owenandroid.keepeye.model.GirlData;
import com.owenandroid.keepeye.utils.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/8.
 */

public class GirlFragment extends Fragment {
    private GridView mGridView;
    private List<GirlData> dataList;//数据源
    private GirlAdapter adapter;
    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl,container,false);
        initData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mGridView = (GridView) view.findViewById(R.id.girl_gridView);
        adapter = new GirlAdapter(getActivity(),dataList);
        mGridView.setAdapter(adapter);
    }

    private void initData() {
        dataList = new ArrayList<>();
        try {
            String fuli = URLEncoder.encode(getString(R.string.girl_fuli),"utf-8");
            url = "http://gank.io/api/search/query/listview/category/" + fuli + "/count/20/page/1";
            L.i(getActivity(),url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
//                L.i(getActivity(),t);
                parsaringJson(t);
            }
        });
    }

    private void parsaringJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            boolean isError = jsonObject.getBoolean("error");
            if (!isError){
                JSONArray results = jsonObject.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject obj = results.getJSONObject(i);
                    String imgUrl = obj.getString("url");
                    L.i(getActivity(),imgUrl);
                    GirlData data = new GirlData(imgUrl);
                    dataList.add(data);
                }
            }else{
                Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
