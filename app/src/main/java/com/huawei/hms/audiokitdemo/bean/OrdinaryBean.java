package com.huawei.hms.audiokitdemo.bean;

public class OrdinaryBean {

    public String name;

    public boolean using;

    public String resId;

    public int type;

    public OrdinaryBean(String name) {
        this.name = name;
    }

    public OrdinaryBean(String name,String resId) {
        this.name = name;
        this.resId = resId;
    }


}
