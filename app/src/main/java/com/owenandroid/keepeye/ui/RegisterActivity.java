package com.owenandroid.keepeye.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.model.MyUser;
import com.owenandroid.keepeye.utils.UtilTools;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/4/9.
 */

public class RegisterActivity extends BaseActivity {
    private EditText edName,edPwd,edPwdAgain,edAge,edDesc;
    private RadioGroup rgSex;
    private Button btnRegister;
    private boolean isBoy = true;//根据radiogroup判断性别是不是男
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        edName = (EditText) findViewById(R.id.regist_ed_name);
        edPwd = (EditText) findViewById(R.id.regist_ed_pwd);
        edPwdAgain = (EditText) findViewById(R.id.regist_ed_pwd_again);
        edAge = (EditText) findViewById(R.id.regist_ed_age);
        edDesc = (EditText) findViewById(R.id.regist_ed_desc);
        rgSex = (RadioGroup) findViewById(R.id.regist_rg_sex);
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == 0){
                    isBoy = true;
                }else{
                    isBoy = false;
                }
            }
        });
        btnRegister = (Button) findViewById(R.id.regist_btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString().trim();
                String password = edPwd.getText().toString().trim();
                String passwordagain = edPwdAgain.getText().toString().trim();
                String age = edAge.getText().toString().trim();
                String description = edDesc.getText().toString().trim();
                //判断输入是否为空
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)
                        && !TextUtils.isEmpty(passwordagain) && !TextUtils.isEmpty(age)){
                    //判断两次输入的密码是否一致
                    if(password.equals(passwordagain)){
                        final ProgressDialog dialog = new ProgressDialog(RegisterActivity.this,ProgressDialog.STYLE_SPINNER);
                        dialog.setMessage("注册中...");
                        dialog.show();
                        MyUser user = new MyUser();
                        user.setUsername(name);
                        user.setPassword(password);
                        user.setAge(Integer.parseInt(age));
                        user.setSex(isBoy);
                        user.setDesc(TextUtils.isEmpty(description)?"这个人很懒，什么也没留下。":description);
                        user.signUp(new SaveListener<MyUser>() {
                            @Override
                            public void done(MyUser myUser, BmobException e) {
                                if (e != null){
                                    //注册成功
                                    dialog.dismiss();
                                    UtilTools.toast(RegisterActivity.this,"注册成功");
                                    finish();
                                }else{
                                    dialog.dismiss();
                                    UtilTools.toast(RegisterActivity.this,"注册失败："+e.toString());
                                }
                            }
                        });
                    }else{
                        UtilTools.toast(RegisterActivity.this,"两次密码不一致");
                    }
                }else{
                    UtilTools.toast(RegisterActivity.this,"必填输入框不能为空");
                }
            }
        });
    }
}
