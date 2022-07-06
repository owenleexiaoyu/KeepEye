package com.owenandroid.keepeye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.model.ExpressData;

import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

public class ExpressAdapter extends BaseAdapter {
    private List<ExpressData> dataList;
    private Context context;
    private LayoutInflater inflater;
    public ExpressAdapter(Context context, List<ExpressData> dataList){
        this.context = context;
        this.dataList = dataList;
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
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listitem_express_info,parent,false);
            viewHolder.tvDateTime = (TextView) convertView.findViewById(R.id.express_list_item_tv_datetime);
            viewHolder.tvRemark = (TextView) convertView.findViewById(R.id.express_list_item_tv_remark);
            viewHolder.tvZone = (TextView) convertView.findViewById(R.id.express_list_item_tv_zone);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ExpressData data = dataList.get(position);
        viewHolder.tvDateTime.setText(data.getDatetime());
        viewHolder.tvRemark.setText(data.getRemark());
        viewHolder.tvZone.setText(data.getZone());
        return convertView;
    }
    class ViewHolder{
        public TextView tvDateTime;
        public TextView tvRemark;
        public TextView tvZone;
    }
}
