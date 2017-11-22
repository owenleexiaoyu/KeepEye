package com.owenandroid.keepeye.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.owenandroid.keepeye.MainActivity;
import com.owenandroid.keepeye.R;
import com.owenandroid.keepeye.model.MyUser;
import com.owenandroid.keepeye.utils.AppConfig;
import com.owenandroid.keepeye.utils.ShareUtils;
import com.owenandroid.keepeye.utils.UtilTools;

import cn.bmob.v3.BmobUser;

/**
 * 程序的入口activity，闪屏页
 * Created by Administrator on 2017/4/9.
 */

public class FlashActivity extends AppCompatActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case AppConfig.HANDLER_FLASH_WHAT:
                    //判断是否是第一次启动APP
                    if (isFirst()){
                        //第一次启动跳转至引导页
                        startActivity(new Intent(FlashActivity.this,GuideActivity.class));
                    }else{
                        //不是第一次启动,判断是否已经登录，如果没登录就跳转至登录页
                        //获取当前的用户，未登录时返回null
//                        MyUser currentUser = BmobUser.getCurrentUser(MyUser.class);
//                        if(currentUser == null){
//                            //未登录，跳转至登录页
//                            startActivity(new Intent(FlashActivity.this,LoginActivity.class));
//                        }else{
//                            //登录过，直接跳转到主界面
//                            startActivity(new Intent(FlashActivity.this,MainActivity.class));
//                        }
                        startActivity(new Intent(FlashActivity.this,MainActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    /**
     * 判断是否是第一次启动，是第一次启动返回true，向key中写入false值
     * 不是第一次启动返回false
     * @return
     */
    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this,AppConfig.SHAREUTILS_KEY_IS_FIRST,true);
        if (isFirst){
            ShareUtils.putBoolean(this,AppConfig.SHAREUTILS_KEY_IS_FIRST,false);
            return true;
        }else{
            return false;
        }
    }

    private TextView tvAppName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        //延时2000ms
        handler.sendEmptyMessageDelayed(AppConfig.HANDLER_FLASH_WHAT,2000);
        tvAppName = (TextView) findViewById(R.id.flash_tv_appname);
        UtilTools.setFontToText(this,tvAppName);
    }
}
