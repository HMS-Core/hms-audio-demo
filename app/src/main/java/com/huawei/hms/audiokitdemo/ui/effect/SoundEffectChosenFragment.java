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

package com.huawei.hms.audiokitdemo.ui.effect;


import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.huawei.hms.audiokit.player.soundeffect.callback.GetSoundEffectListCallback;
import com.huawei.hms.audiokit.player.soundeffect.callback.GetSoundEffectListListener;
import com.huawei.hms.audiokit.player.soundeffect.callback.SetSoundEffectListener;
import com.huawei.hms.audiokit.soundeffect.bean.HwAudioEffectItem;
import com.huawei.hms.audiokitdemo.R;
import com.huawei.hms.audiokitdemo.adapter.ChooseAdapter;
import com.huawei.hms.audiokitdemo.adapter.ChosenSoundEffectSwitchAdapter;
import com.huawei.hms.audiokitdemo.bean.OrdinaryBean;
import com.huawei.hms.audiokitdemo.bean.SoundEffectBean;
import com.huawei.hms.audiokitdemo.constant.SoundEffectConstant;
import com.huawei.hms.audiokitdemo.helper.SoundEffectHelper;
import com.huawei.hms.audiokitdemo.sdk.PlayHelper;
import com.huawei.hms.audiokitdemo.utils.SoundEffectUtils;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class SoundEffectChosenFragment extends BaseUIFragment {
    private static final String TAG = "SoundEffectChosenFragment";

    private RecyclerView recyclerview;

    private DelegateAdapter delegateAdapter = null;

    private ChooseAdapter chooseAdapter = null;

    private ChosenSoundEffectSwitchAdapter chosenSoundEffectSwitchAdapter = null;

    public static SoundEffectChosenFragment newInstance() {
        SoundEffectChosenFragment fragment = new SoundEffectChosenFragment();
        return fragment;
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.sound_effect_player;
    }

    @Override
    protected void initViews(View view) {
        recyclerview = view.findViewById(R.id.recyclerview);
        chosenSoundEffectSwitchAdapter = new ChosenSoundEffectSwitchAdapter(new LinearLayoutHelper(), getActivity());
        VirtualLayoutManager manager = new VirtualLayoutManager(getContext());
        delegateAdapter = new DelegateAdapter(manager);
        //delegateAdapter.addAdapter(new ChooseHeadAdapter(new LinearLayoutHelper(), getActivity()));
        delegateAdapter.addAdapter(chosenSoundEffectSwitchAdapter);
        chooseAdapter = new ChooseAdapter(new LinearLayoutHelper(), getActivity());
        recyclerview.setLayoutManager(manager);
        delegateAdapter.addAdapter(chooseAdapter);
        recyclerview.setAdapter(delegateAdapter);
        initData();
        setListener();
    }

    private void initData() {
        HwAudioEffectItem hwAudioEffectItem = SoundEffectHelper.getInstance().getNowUsingSoundEffect();
        String resId = hwAudioEffectItem != null ? hwAudioEffectItem.getResId():"";
        for (SoundEffectBean soundEffectBean : SoundEffectUtils.getSoundEffectBeanList()) {
            if (!TextUtils.isEmpty(resId) && resId.equals(soundEffectBean.resId)) {
                soundEffectBean.using = true;
                continue;
            }
            soundEffectBean.using = false;
        }
        chooseAdapter.setSoundEffectBeans(SoundEffectUtils.getSoundEffectBeanList());
        SoundEffectHelper.getInstance().getChosenSoundEffect(new GetSoundEffectListCallback(new ChosenChosenGetSoundEffectListListener()));
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (chooseAdapter != null && isVisibleToUser) {
            chooseAdapter.getSoundEffectBeans().clear();
            initData();
        }
    }

    private void setListener() {
        chosenSoundEffectSwitchAdapter.setSoundEffectSwitchOnClick(new ChosenSoundEffectSwitchAdapter.SoundEffectSwitchOnClick() {
            @Override
            public void click(boolean open) {
                if (!open) {
                    chooseAdapter.reset();
                }
            }
        });
        chooseAdapter.setChooseClickListener(new ChooseAdapter.ChooseClickListener() {
            @Override
            public void click(int pos) {
                if (SoundEffectUtils.getHwAudioEffectItems().size() == 0)
                    return;
                boolean isNotSet = false;
                for (int i = 0; i < chooseAdapter.getSoundEffectBeans().size(); i++) {
                    SoundEffectBean bean = chooseAdapter.getSoundEffectBeans().get(i);
                    if (i == pos) {
                        if (bean.using) {
                            isNotSet = true;
                        } else {
                            bean.using = true;
                        }
                    } else {
                        bean.using = false;
                    }
                }
                if (isNotSet)
                    return;
                resetOrdinaryEffect();
                chooseAdapter.notifyDataSetChanged();
                String effectId = "";
                if (pos == 0) {
                    effectId = SoundEffectConstant.BEAUTIFUL_VOICE;
                } else if (pos == 1) {
                    effectId = SoundEffectConstant.SOUND_ETHEREAL_SOFT;
                } else if (pos == 2) {
                    effectId = SoundEffectConstant.THREE_D_SURROUND;
                } else if (pos == 3) {
                    effectId = SoundEffectConstant.ULTRA_LOW_STRESS;
                } else if (pos == 4) {
                    effectId = SoundEffectConstant.CAR_SOUND;
                }
                if (SoundEffectUtils.getHwAudioEffectItems().size() == 0)
                    return;
                for (HwAudioEffectItem hwAudioEffectItem: SoundEffectUtils.getHwAudioEffectItems()) {
                    if (effectId.equals(hwAudioEffectItem.getName())) {
                        PlayHelper.getInstance().setEffect(hwAudioEffectItem, new SoundEffectListener(pos));
                        break;
                    }
                }
            }
        });
    }

    private void resetOrdinaryEffect() {
        for(OrdinaryBean ordinaryBean : SoundEffectUtils.getOrdinaryBeanList()) {
            ordinaryBean.using = false;
        }
    }

    private class SoundEffectListener implements SetSoundEffectListener {
        private static final String TAG = "SoundEffectListener";

        private int pos;

        public SoundEffectListener(int pos) {
            this.pos = pos;
        }

        @Override
        public void onSuccess(int i) {
            Log.i(TAG, "onSuccess" + i);
            // sound effect set success
            if (i == 0) {
                chooseAdapter.setSelect(pos, true);
                chosenSoundEffectSwitchAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(int i, String s) {
            Log.i(TAG, "onError" + i + ";" + s);
        }
    }

    static class ChosenChosenGetSoundEffectListListener implements GetSoundEffectListListener {
        @Override
        public void onSuccess(List<HwAudioEffectItem> list) {
            Log.i(TAG, "onSuccess list size == " + list.size());
            SoundEffectUtils.getHwAudioEffectItems().clear();
            SoundEffectUtils.getHwAudioEffectItems().addAll(list);
        }

        @Override
        public void onError(int i, String s) {
            Log.i(TAG, "onError" + s + "; i ==" + i);
        }
    };

}
