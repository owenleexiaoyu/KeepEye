package com.owenandroid.keepeye.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/4/9.
 */

public class UtilTools {
    //为textview设置自定义字体
    public static void setFontToText(Context mContext, TextView textView){
        Typeface fonttf = Typeface.createFromAsset(mContext.getAssets(), "fonts/font.ttf");
        textView.setTypeface(fonttf);
    }
    //输出Toast信息
    public static void toast(Context context,String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
