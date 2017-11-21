package com.owenandroid.keepeye.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.adapter.ArticleAdapter;
import com.owenandroid.keepeye.model.ArticleData;
import com.owenandroid.keepeye.ui.ArticleContentActivity;
import com.owenandroid.keepeye.utils.AppConfig;
import com.owenandroid.keepeye.utils.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/8.
 */

public class ArticleFragment extends Fragment {
    private ListView listView;
    private List<ArticleData> dataList;
    private ArticleAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article,container,false);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        dataList = new ArrayList<>();
        HttpParams params = new HttpParams();
        params.put("showapi_appid",AppConfig.SHOWAPI_APPID);
        params.put("showapi_sign",AppConfig.SHOWAPI_SECRET);
        RxVolley.post(AppConfig.WEIXIN_AIRTICLE_URL, params, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                L.i(getActivity(),t);
                parsingJson(t);

            }
        });
    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonObject1 = jsonObject.getJSONObject("showapi_res_body");
            JSONObject jsonObject2 = jsonObject1.getJSONObject("pagebean");
            JSONArray contentlist = jsonObject2.getJSONArray("contentlist");
            for (int i = 0; i < contentlist.length(); i++) {
                JSONObject json = contentlist.getJSONObject(i);
                ArticleData data = new ArticleData();
                data.setTitle(json.getString("title"));
                data.setLabel(json.getString("typeName"));
                data.setUserName(json.getString("userName"));
                data.setArticleUrl(json.getString("url"));
                data.setImageUrl(json.getString("contentImg"));
                dataList.add(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.article_ll_list);
        adapter = new ArticleAdapter(getActivity(),dataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArticleData data = dataList.get(position);
                String url = data.getArticleUrl();
                String title = data.getTitle();
                Intent intent = new Intent(getActivity(), ArticleContentActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });
    }
}
