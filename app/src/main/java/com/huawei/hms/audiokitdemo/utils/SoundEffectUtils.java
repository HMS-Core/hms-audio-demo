package com.huawei.hms.audiokitdemo.utils;

import com.huawei.hms.audiokit.soundeffect.bean.HwAudioEffectItem;
import com.huawei.hms.audiokitdemo.R;
import com.huawei.hms.audiokitdemo.bean.OrdinaryBean;
import com.huawei.hms.audiokitdemo.bean.SoundEffectBean;
import com.huawei.hms.audiokitdemo.constant.SoundEffectConstant;

import java.util.ArrayList;
import java.util.List;

public class SoundEffectUtils {
    private static final List<SoundEffectBean> soundEffectBeanList = new ArrayList<>();

    private static final List<OrdinaryBean> ordinaryBeanList = new ArrayList<>();

    private static final List<HwAudioEffectItem> hwAudioEffectItems = new ArrayList<>();

    private static final List<HwAudioEffectItem> ordHwAudioEffectItems = new ArrayList<>();

    static {
        soundEffectBeanList.add(new SoundEffectBean(R.drawable.ic_effects_3d, ResUtils.getString(R.string.sound_effect_voice), ResUtils.getString(R.string.sound_bass_des), false, "beautiful_voice"));
        soundEffectBeanList.add(new SoundEffectBean(R.drawable.ic_effects_concert_hall, ResUtils.getString(R.string.sound_ethereal), ResUtils.getString(R.string.sound_ethereal_des_cn), false, "sound_ethereal_soft"));
        soundEffectBeanList.add(new SoundEffectBean(R.drawable.ic_effects_dts, ResUtils.getString(R.string.sound_roation_3d), ResUtils.getString(R.string.sound_3d_des), false, "three_d_surround"));
        soundEffectBeanList.add(new SoundEffectBean(R.drawable.ic_effects_car, ResUtils.getString(R.string.strong_bass), ResUtils.getString(R.string.sound_roation_3d_des), false, "ultra_low_stress"));
        soundEffectBeanList.add(new SoundEffectBean(R.drawable.ic_effects_electronic, ResUtils.getString(R.string.car), ResUtils.getString(R.string.sound_fantasy_acg_des), false, "car_sound"));

        ordinaryBeanList.add(new OrdinaryBean(ResUtils.getString(R.string.dolby_rock), SoundEffectConstant.ROCK_SOUND));
        ordinaryBeanList.add(new OrdinaryBean(ResUtils.getString(R.string.dolby_jazz), SoundEffectConstant.JAZZ_SOUND));
        ordinaryBeanList.add(new OrdinaryBean(ResUtils.getString(R.string.dolby_pop), SoundEffectConstant.POP_SOUND));
        ordinaryBeanList.add(new OrdinaryBean(ResUtils.getString(R.string.dolby_classical), SoundEffectConstant.CLASSICAL_SOUND));

        ordinaryBeanList.add(new OrdinaryBean(ResUtils.getString(R.string.dolby_dance), SoundEffectConstant.DANCE_MUSIC));
        ordinaryBeanList.add(new OrdinaryBean(ResUtils.getString(R.string.sound_soft_treble), SoundEffectConstant.SOFT_PITCH));
        ordinaryBeanList.add(new OrdinaryBean(ResUtils.getString(R.string.sound_voice), SoundEffectConstant.HUMAN_VOICE));
        ordinaryBeanList.add(new OrdinaryBean(ResUtils.getString(R.string.dolby_blues), SoundEffectConstant.BLUES_SOUND));
        ordinaryBeanList.add(new OrdinaryBean(ResUtils.getString(R.string.sound_sluggish), SoundEffectConstant.SLUGGISH_SOUND));
        ordinaryBeanList.add(new OrdinaryBean(ResUtils.getString(R.string.sound_country), SoundEffectConstant.VILLAGE_SOUND));
        ordinaryBeanList.add(new OrdinaryBean(ResUtils.getString(R.string.sound_electronic), SoundEffectConstant.ELECTRONIC_SOUND));
    }

    public static List<SoundEffectBean> getSoundEffectBeanList() {
        return soundEffectBeanList;
    }

    public static List<OrdinaryBean> getOrdinaryBeanList() {
        return ordinaryBeanList;
    }

    public static List<HwAudioEffectItem> getHwAudioEffectItems() {
        return hwAudioEffectItems;
    }

    public static List<HwAudioEffectItem> getOrdHwAudioEffectItems() {
        return ordHwAudioEffectItems;
    }
}
