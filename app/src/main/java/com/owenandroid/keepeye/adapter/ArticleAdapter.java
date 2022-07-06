package com.owenandroid.keepeye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.model.ArticleData;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ArticleAdapter extends BaseAdapter {
    private List<ArticleData> dataList;
    private Context context;
    private LayoutInflater inflater;
    public ArticleAdapter(Context context, List<ArticleData> dataList) {
        this.dataList = dataList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listitem_article,parent,false);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.article_item_tv_title);
            holder.tvLabel = (TextView) convertView.findViewById(R.id.article_item_tv_label);
            holder.tvUserName = (TextView) convertView.findViewById(R.id.article_item_tv_username);
            holder.imgPicture = (ImageView) convertView.findViewById(R.id.article_item_img_image);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        ArticleData data = dataList.get(position);
        holder.tvTitle.setText(data.getTitle());
        holder.tvLabel.setText(data.getLabel());
        holder.tvUserName.setText(data.getUserName());
        //使用Picasso加载文章图片
        Picasso.get().load(data.getImageUrl()).resize(120,70).into(holder.imgPicture);
        return convertView;
    }
    class ViewHolder{
        private TextView tvTitle;
        private TextView tvLabel;
        private TextView tvUserName;
        private ImageView imgPicture;
    }
}
