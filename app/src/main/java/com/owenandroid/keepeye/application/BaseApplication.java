package com.owenandroid.keepeye.application;

import android.app.Application;

import com.owenandroid.keepeye.utils.AppConfig;
import com.tencent.bugly.crashreport.CrashReport;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/4/8.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), AppConfig.BUGLY_APP_ID, false);
        Bmob.initialize(getApplicationContext(),AppConfig.BMOB_APP_ID);
    }
}
