package com.owenandroid.keepeye.model;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2017/4/9.
 */

public class MyUser extends BmobUser {
    private int age;//年龄
    private String desc;//简介
    private boolean sex;//性别：男生为true，女生为false
    private BmobFile icon;//头像

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public BmobFile getIcon() {
        return icon;
    }

    public void setIcon(BmobFile icon) {
        this.icon = icon;
    }
}
