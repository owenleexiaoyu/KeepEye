package com.owenandroid.keepeye.model;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ChatMsg {
    public static final int MSG_TYPE_IN = 0;
    public static final int MSG_TYPE_OUT = 1;

    private String text;
    private int type;

    public ChatMsg(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
