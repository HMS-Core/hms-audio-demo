package com.huawei.hms.audiokitdemo.bean;

public class SoundEffectBean {


    public int icon;

    public String soundEffectName;

    public String desc;

    public boolean using;

    public String resId;

    public SoundEffectBean(int icon, String soundEffectName, String desc, boolean using) {
        this.icon = icon;
        this.soundEffectName = soundEffectName;
        this.desc = desc;
        this.using = using;
    }

    public SoundEffectBean(int icon, String soundEffectName, String desc, boolean using, String resId) {
        this.icon = icon;
        this.soundEffectName = soundEffectName;
        this.desc = desc;
        this.using = using;
        this.resId = resId;
    }
}
