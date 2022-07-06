package com.owenandroid.keepeye.zhihu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.model.ZhihuDailyData;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017-11-22.
 */

public class ZhihuDailyAdapter extends RecyclerView.Adapter<ZhihuDailyAdapter.ViewHolder>{

    private List<ZhihuDailyData> mDataList;
    private Context mContext;

    public ZhihuDailyAdapter(Context context, List<ZhihuDailyData> dataList){
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_zhihustory,parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ZhihuDailyData data = mDataList.get(position);
        holder.tvTitle.setText(data.getTitle());
        Picasso.get()
                .load(data.getImage())
                .resize(150,80)
                .into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView ivImage;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.item_zhihu_tv_title);
            ivImage = (ImageView) itemView.findViewById(R.id.item_zhihu_iv_image);
        }
    }
}
