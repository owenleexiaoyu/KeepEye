package com.owenandroid.keepeye.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.model.ChatMsg;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<ChatMsg> msgList;

    public ChatAdapter(List<ChatMsg> msgList) {
        this.msgList = msgList;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.listitem_chat,parent,false);
        ChatViewHolder holder = new ChatViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        ChatMsg msg = msgList.get(position);
        if (msg.getType() == ChatMsg.MSG_TYPE_IN){
            //收到的消息
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftText.setText(msg.getText());
        }else if (msg.getType() == ChatMsg.MSG_TYPE_OUT){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightText.setText(msg.getText());
        }
    }

    class ChatViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout leftLayout;
        private RelativeLayout rightLayout;
        private TextView leftText;
        private TextView rightText;
        public ChatViewHolder(View itemView) {
            super(itemView);
            leftLayout = (LinearLayout) itemView.findViewById(R.id.chat_left_root);
            rightLayout = (RelativeLayout) itemView.findViewById(R.id.chat_right_root);
            leftText = (TextView) itemView.findViewById(R.id.chat_left_text);
            rightText = (TextView) itemView.findViewById(R.id.chat_right_text);
        }
    }

}
