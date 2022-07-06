package com.owenandroid.keepeye.zhihu;

import androidx.annotation.NonNull;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.owenandroid.keepeye.model.ZhihuDailyData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-11-22.
 */

public class ZhihuDailyPresenter implements ZhihuDailyContract.Presenter {
    private String url;
    private ZhihuDailyContract.View view;
    private List<ZhihuDailyData> dataList;
    public ZhihuDailyPresenter(String url, @NonNull ZhihuDailyContract.View view){
        this.url = url;
        this.view = view;
        //关联 Presenter 和 View
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadData();
    }

    // 通过知乎日报的API来获取消息列表
    private void getStoriesList(String url) {
        dataList = new ArrayList<>();
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                try {
                    JSONObject object = new JSONObject(t);
                    JSONArray storyArray = object.getJSONArray("stories");
                    JSONArray topStoryArray = object.getJSONArray("top_stories");

                    for (int i = 0;i<topStoryArray.length();i++){
                        JSONObject obj = topStoryArray.getJSONObject(i);
                        ZhihuDailyData data = new ZhihuDailyData();
                        data.setImage(obj.getString("image"));
                        data.setTitle(obj.getString("title"));
                        data.setId(obj.getInt("id"));
                        dataList.add(data);
                    }
                    for (int i = 0;i<storyArray.length();i++){
                        JSONObject obj = storyArray.getJSONObject(i);
                        ZhihuDailyData data = new ZhihuDailyData();
                        data.setImage(obj.getString("images"));
                        data.setTitle(obj.getString("title"));
                        data.setId(obj.getInt("id"));
                        dataList.add(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void loadData() {
        view.showLoading();
        getStoriesList(url);
        //将列表传递给View对象
        view.showStoties(dataList);
        view.hideLoading();
    }
}
