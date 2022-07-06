package com.owenandroid.keepeye.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.adapter.ChatAdapter;
import com.owenandroid.keepeye.model.ChatMsg;
import com.owenandroid.keepeye.utils.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/8.
 */

public class ButlerFragment extends Fragment implements View.OnClickListener {
    private RecyclerView chatView;
    private List<ChatMsg> dataList;
    private Button btnSend;
    private EditText edMsg;
    private ChatAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_butler,container,false);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        dataList = new ArrayList<>();
        dataList.add(new ChatMsg(ChatMsg.MSG_TYPE_IN,"你好,我是机器人Allen"));
    }

    private void initView(View view) {
        btnSend = (Button) view.findViewById(R.id.butler_btn_send);
        btnSend.setOnClickListener(this);
        edMsg = (EditText) view.findViewById(R.id.butler_ed_msg);
        chatView = (RecyclerView) view.findViewById(R.id.butler_rv_chatting);
        chatView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ChatAdapter(dataList);
        chatView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        String msg = edMsg.getText().toString().trim();
        if (!TextUtils.isEmpty(msg)){
            if (msg.length() < 30){
                addMsgToChatList(ChatMsg.MSG_TYPE_OUT,msg);
                edMsg.setText("");
                HttpParams params = new HttpParams();
                params.put("key",AppConfig.ROBOT_APP_ID);
                params.put("info",msg);
                RxVolley.post(AppConfig.TULING_API, params, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        super.onSuccess(t);
//                        Toast.makeText(getActivity(),t,Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject(t);
                            String text = jsonObject.getString("text");
                            addMsgToChatList(ChatMsg.MSG_TYPE_IN,text);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }else{
                Toast.makeText(getActivity(), "输入文字过长", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(), "输入框不能为空", Toast.LENGTH_SHORT).show();
        }
    }
    public void addMsgToChatList(int type, String text){
        dataList.add(new ChatMsg(type,text));
        adapter.notifyItemInserted(dataList.size()-1);
        chatView.scrollToPosition(dataList.size()-1);
    }
}
