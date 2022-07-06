package com.owenandroid.keepeye.utils;

import android.content.Context;
import android.util.Log;

/**
 * Log的封装类
 * Created by Administrator on 2017/4/9.
 */

public class L {
    //调式开关
    private static final boolean DEBUG = true;
    //四个等级，tag为当前所在类名
    public static void i(Context context,String text){
        Log.i(context.getClass().getSimpleName(),text);
    }
    public static void d(Context context,String text){
        Log.d(context.getClass().getSimpleName(),text);
    }
    public static void w(Context context,String text){
        Log.w(context.getClass().getSimpleName(),text);
    }
    public static void e(Context context,String text){
        Log.e(context.getClass().getSimpleName(),text);
    }
}
