package com.huawei.hms.audiokitdemo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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

    @Override
    public void onBindViewHolder(@NonNull SoundSwitchViewHolder holder, int position) {
        // 获取普通音效是否打开
        boolean soundOrdinaryLightOn = SoundEffectHelper.getInstance().getSoundOrdinaryLightOn();
        Log.i(TAG, ";SoundOrdinaryLightOn == " + soundOrdinaryLightOn);
        if (soundOrdinaryLightOn) {
            opening = true;
            holder.soundEffectSwitch.setImageDrawable(context.getResources().getDrawable(R.drawable.btn_sound_switch_light_on));
        } else {
            opening = false;
            holder.soundEffectSwitch.setImageDrawable(context.getResources().getDrawable(R.drawable.btn_sound_switch_light_off));
        }
        setEffectName();
        holder.tvSound.setText(TextUtils.isEmpty(currentName)? " " : currentName);
        holder.soundEffectSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soundEffectSwitchOnClick != null) {
                    opening = !opening;
                    if (opening) {
                        SoundEffectHelper.getInstance().setEffectSwitch(true);
                        SoundEffectHelper.getInstance().setSoundOrdinaryLightOn(true);
                    } else {
                        SoundEffectHelper.getInstance().setEffectSwitch(false);
                        SoundEffectHelper.getInstance().setSoundOrdinaryLightOn(false);
                    }
                    soundEffectSwitchOnClick.click(opening);
                    notifyDataSetChanged();
                }
            }
        });

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
