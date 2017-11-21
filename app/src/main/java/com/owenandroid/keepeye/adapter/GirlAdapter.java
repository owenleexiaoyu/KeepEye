package com.owenandroid.keepeye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.model.GirlData;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017-06-03.
 */

public class GirlAdapter extends BaseAdapter {
    private Context mContext;
    private List<GirlData> mDataList;
    private LayoutInflater inflater;

    public GirlAdapter(Context mContext, List<GirlData> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        ViewHOlder hOlder = null;
        if (convertView == null){
            hOlder = new ViewHOlder();
            convertView = inflater.inflate(R.layout.griditem_girlphoto,parent,false);
            hOlder.imgGirl = (ImageView) convertView.findViewById(R.id.griditem_img_girlpic);
            convertView.setTag(hOlder);
        }else{
            hOlder = (ViewHOlder) convertView.getTag();
        }
        GirlData data = mDataList.get(position);
        Picasso.with(mContext).load(data.getImgUrl()).resize(width/2 - 20,width/2).into(hOlder.imgGirl);
        return convertView;
    }
    class ViewHOlder{
        private ImageView imgGirl;
    }
}
