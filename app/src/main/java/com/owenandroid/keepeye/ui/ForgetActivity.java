package com.owenandroid.keepeye.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.model.MyUser;
import com.owenandroid.keepeye.utils.UtilTools;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

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
                MyUser currentUser = BmobUser.getCurrentUser(MyUser.class);
                if (currentUser != null){
                    //有用户缓存的情况下重置密码
                    String email = edEmail.getText().toString().trim();
                    BmobUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null){
                                UtilTools.toast(ForgetActivity.this,"发送邮件成功");
                            }else{
                                UtilTools.toast(ForgetActivity.this,"发送邮件失败："+e.toString());
                            }
                        }
                    });
                }
                break;
        }
    }
}
