package com.owenandroid.keepeye.model;


/**
 * Created by Administrator on 2017/4/9.
 */

public class MyUser {
    private int age;//年龄
    private String desc;//简介
    private boolean sex;//性别：男生为true，女生为false

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

}
