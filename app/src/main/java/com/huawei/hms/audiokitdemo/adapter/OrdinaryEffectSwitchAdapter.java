package com.huawei.hms.audiokitdemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.huawei.hms.audiokit.soundeffect.bean.HwAudioEffectItem;
import com.huawei.hms.audiokitdemo.R;
import com.huawei.hms.audiokitdemo.bean.OrdinaryBean;
import com.huawei.hms.audiokitdemo.constant.SoundEffectConstant;
import com.huawei.hms.audiokitdemo.helper.SoundEffectHelper;
import com.huawei.hms.audiokitdemo.utils.SoundEffectUtils;

import androidx.annotation.NonNull;

public class OrdinaryEffectSwitchAdapter extends BaseSoundEffectSwitchAdapter {
    private static final String TAG = "OrdinaryEffectSwitchAdapter";

    public OrdinaryEffectSwitchAdapter(LinearLayoutHelper linearLayoutHelper, Context context) {
        super(linearLayoutHelper, context);
    }

    @NonNull
    @Override
    public SoundSwitchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SoundSwitchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_effect_ordinary_switch, parent, false));
    }


    protected void setEffectName() {
        HwAudioEffectItem hwAudioEffectItem = SoundEffectHelper.getInstance().getNowUsingSoundEffect();
        if (hwAudioEffectItem == null)
            return;
        String resId = hwAudioEffectItem.getName();
        if (SoundEffectConstant.ORDINARY_SOUNDEFFECT_CUSTOM_TYPE == hwAudioEffectItem.getType()) {
            for (HwAudioEffectItem audioEffectItem : SoundEffectUtils.getOrdHwAudioEffectItems()) {
                if (SoundEffectConstant.ORDINARY_SOUNDEFFECT_CUSTOM_TYPE == hwAudioEffectItem.getType()) {
                    if (audioEffectItem == null) {
                        continue;
                    }
                    if (audioEffectItem.getResId().equals(hwAudioEffectItem.getResId())) {
                        currentName = audioEffectItem.getName();
                        break;
                    }
                }
            }
        } else {
            for (OrdinaryBean ordinaryBean : SoundEffectUtils.getOrdinaryBeanList()) {
                if (ordinaryBean.resId.equals(resId)) {
                    currentName = ordinaryBean.name;
                    break;
                }
            }
        }

        Log.i(TAG, "resId == " + resId);
    }
}
