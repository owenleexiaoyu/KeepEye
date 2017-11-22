package com.owenandroid.keepeye.zhihu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.model.ZhihuDailyData;

import java.util.List;

/**
 * Created by Administrator on 2017-11-22.
 */

public class ZhihuDailyFragment extends Fragment implements ZhihuDailyContract.View{
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView = null;

    private ZhihuDailyContract.Presenter mPresenter;
    private ZhihuDailyAdapter adapter = null;
    private LinearLayoutManager layoutManager = null;

    private List<ZhihuDailyData> mDataList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhihudaily, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //初始化控件
        mSwipeRefreshLayout = (SwipeRefreshLayout)
                view.findViewById(R.id.zhihudaily_swiperefreshlayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 刷新数据
                if(mDataList != null){
                    mDataList.clear();
                }
                mPresenter.loadData();
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.zhihudaily_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void setPresenter(ZhihuDailyContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showStoties(List<ZhihuDailyData> dataList) {
        adapter = new ZhihuDailyAdapter(getActivity(), dataList);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        //启动 Presenter
        mPresenter.start();
    }
}
