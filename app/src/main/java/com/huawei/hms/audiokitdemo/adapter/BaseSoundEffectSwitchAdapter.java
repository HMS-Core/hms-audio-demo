/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.huawei.hms.audiokitdemo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.huawei.hms.audiokit.soundeffect.bean.HwAudioEffectItem;
import com.huawei.hms.audiokitdemo.R;
import com.huawei.hms.audiokitdemo.bean.SoundEffectBean;
import com.huawei.hms.audiokitdemo.helper.SoundEffectHelper;
import com.huawei.hms.audiokitdemo.utils.ResUtils;
import com.huawei.hms.audiokitdemo.utils.SoundEffectUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Function Description
 *
 * @since 2021-04-27
 */
class BaseSoundEffectSwitchAdapter extends DelegateAdapter.Adapter<BaseSoundEffectSwitchAdapter.SoundSwitchViewHolder> {
    protected static final String TAG = "SoundEffectSwitchAdapter";
    protected LinearLayoutHelper linearLayoutHelper;
    protected Context context;
    protected String currentName;
    protected boolean opening;
    protected SoundEffectSwitchOnClick soundEffectSwitchOnClick;

    BaseSoundEffectSwitchAdapter(LinearLayoutHelper linearLayoutHelper, Context context) {
        this.linearLayoutHelper = linearLayoutHelper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        linearLayoutHelper.setMarginTop(ResUtils.dp2Px(context, 24));
        linearLayoutHelper.setMarginLeft(ResUtils.dp2Px(context, 24));
        linearLayoutHelper.setMarginRight(ResUtils.dp2Px(context, 24));
        return linearLayoutHelper;
    }

    @NonNull
    @Override
    public SoundSwitchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SoundSwitchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_effect_chosen_switch, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SoundSwitchViewHolder holder, int position) {
        boolean isEffectOn = SoundEffectHelper.getInstance().isEffectOn();
        Log.i(TAG, ";isEffectOn == " + isEffectOn);
        if (isEffectOn) {
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
                    } else {
                        SoundEffectHelper.getInstance().setEffectSwitch(false);
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
        Log.i(TAG, "resId == " + resId);
        for (SoundEffectBean soundEffectBean : SoundEffectUtils.getSoundEffectBeanList()) {
            if (soundEffectBean.resId.equals(resId)) {
                currentName = soundEffectBean.soundEffectName;
                break;
            }
        }
    }

    public void setSoundEffectSwitchOnClick(SoundEffectSwitchOnClick soundEffectSwitchOnClick) {
        this.soundEffectSwitchOnClick = soundEffectSwitchOnClick;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
        notifyDataSetChanged();
    }

    public void setOpening(boolean opening) {
        this.opening = opening;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public interface SoundEffectSwitchOnClick {
        void click(boolean open);
    }

    class SoundSwitchViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView soundEffectSwitch;

        TextView tvSound;

        public SoundSwitchViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSound = itemView.findViewById(R.id.tv_sound);
            soundEffectSwitch = itemView.findViewById(R.id.sound_effect_switch);
        }
    }
}
