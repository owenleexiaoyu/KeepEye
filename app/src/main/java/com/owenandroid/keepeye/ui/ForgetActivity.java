package com.owenandroid.keepeye.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.owenandroid.keepeye.R;


/**
 * Created by Administrator on 2017/4/10.
 */
public class ForgetActivity extends BaseActivity implements View.OnClickListener {
    private EditText edEmail;//输入邮箱地址
    private Button btnResetPwd;//重置密码
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initView();
    }

    private void initView() {
        edEmail = (EditText) findViewById(R.id.forget_ed_email);
        btnResetPwd = (Button) findViewById(R.id.forget_btn_reset_pwd);
        btnResetPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forget_btn_reset_pwd:
                break;
        }
    }
}
