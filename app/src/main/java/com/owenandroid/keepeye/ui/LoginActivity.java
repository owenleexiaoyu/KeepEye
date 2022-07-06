package com.owenandroid.keepeye.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.owenandroid.keepeye.MainActivity;
import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.model.MyUser;
import com.owenandroid.keepeye.utils.AppConfig;
import com.owenandroid.keepeye.utils.L;
import com.owenandroid.keepeye.utils.ShareUtils;
import com.owenandroid.keepeye.utils.UtilTools;

//import cn.bmob.v3.exception.BmobException;
//import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/4/9.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edName,edPassword;
    private Button btnToRegister,btnLogin,btnForgetPwd;
    private CheckBox cbKeepInput;//记住账号密码

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        edName = (EditText) findViewById(R.id.login_ed_name);
        edPassword = (EditText) findViewById(R.id.login_ed_password);
        btnToRegister = (Button) findViewById(R.id.login_btn_toregister);
        btnLogin = (Button) findViewById(R.id.login_btn_login);
        btnToRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        cbKeepInput = (CheckBox) findViewById(R.id.login_cb_keep_input);
        edName.setText(ShareUtils.getString(this,AppConfig.SHAREUTILS_KEY_USER_NAME_INPUT, ""));
        edPassword.setText(ShareUtils.getString(this,AppConfig.SHAREUTILS_KEY_USER_PASSWORD_INPUT, ""));
        btnForgetPwd = (Button) findViewById(R.id.login_btn_forget_pwd);
        btnForgetPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn_toregister:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.login_btn_login:
                final String name = edName.getText().toString().trim();
                final String password = edPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)){
                    final ProgressDialog dialog = new ProgressDialog(this,ProgressDialog.STYLE_SPINNER);
                    dialog.setMessage("登录中...");
                    dialog.show();
                    MyUser user = new MyUser();
//                    user.setUsername(name);
//                    user.setPassword(password);
//                    user.login(new SaveListener<MyUser>() {
//                        @Override
//                        public void done(MyUser myUser, BmobException e) {
//                            if (e == null){
//                                //判断是否要记住账号密码
//                                if(cbKeepInput.isChecked()){
//                                    ShareUtils.putString(LoginActivity.this,
//                                            AppConfig.SHAREUTILS_KEY_USER_NAME_INPUT,name);
//                                    ShareUtils.putString(LoginActivity.this,
//                                            AppConfig.SHAREUTILS_KEY_USER_PASSWORD_INPUT, password);
//                                }else{
//                                    ShareUtils.delete(LoginActivity.this,
//                                            AppConfig.SHAREUTILS_KEY_USER_NAME_INPUT);
//                                    ShareUtils.delete(LoginActivity.this,
//                                            AppConfig.SHAREUTILS_KEY_USER_PASSWORD_INPUT);
//                                }
//                                dialog.dismiss();
//                                UtilTools.toast(LoginActivity.this,"登录成功");
//                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                finish();
//                            }else{
//                                dialog.dismiss();
//                                UtilTools.toast(LoginActivity.this,"登录失败");
//                                L.e(LoginActivity.this,e.toString());
//                            }
//                        }
//                    });
                }else{
                    UtilTools.toast(LoginActivity.this,"用户名密码不能为空");
                }
                break;
            case R.id.login_btn_forget_pwd:
                startActivity(new Intent(this,ForgetActivity.class));
                break;
        }
    }
}
