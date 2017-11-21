package com.owenandroid.keepeye.model;

/**
 * Created by Administrator on 2017/4/11.
 */

public class ExpressData {
    private String zone;//快件当时所在区域
    private String remark;//物流事件的描述
    private String datetime;// 物流事件发生的时间
    public ExpressData(String zone, String remark, String datetime){
        this.datetime = datetime;
        this.zone = zone;
        this.remark = remark;
    }

    public String getZone() {
        return zone;
    }

    public String getRemark() {
        return remark;
    }

    public String getDatetime() {
        return datetime;
    }
}
