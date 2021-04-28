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
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.huawei.hms.audiokit.player.soundeffect.callback.GetSoundEffectListCallback;
import com.huawei.hms.audiokit.player.soundeffect.callback.GetSoundEffectListListener;
import com.huawei.hms.audiokit.player.soundeffect.callback.SetSoundEffectListener;
import com.huawei.hms.audiokit.soundeffect.bean.HwAudioEffectItem;
import com.huawei.hms.audiokitdemo.R;
import com.huawei.hms.audiokitdemo.adapter.OrdinaryAdapter;
import com.huawei.hms.audiokitdemo.adapter.OrdinaryEffectSwitchAdapter;
import com.huawei.hms.audiokitdemo.bean.OrdinaryBean;
import com.huawei.hms.audiokitdemo.bean.SoundEffectBean;
import com.huawei.hms.audiokitdemo.constant.SoundEffectConstant;
import com.huawei.hms.audiokitdemo.helper.SoundEffectHelper;
import com.huawei.hms.audiokitdemo.sdk.PlayHelper;
import com.huawei.hms.audiokitdemo.utils.SoundEffectUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class SoundEffectOrdinaryFragment extends BaseUIFragment {
    private static final String TAG = "SoundEffectOrdinaryFragment";

    private RecyclerView recyclerview;

    private DelegateAdapter delegateAdapter = null;

    private OrdinaryAdapter adapter;

    private OrdinaryEffectSwitchAdapter ordinaryEffectSwitchAdapter = null;

    public static SoundEffectOrdinaryFragment newInstance() {
        SoundEffectOrdinaryFragment fragment = new SoundEffectOrdinaryFragment();
        return fragment;
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.sound_effect_ordinary_layout;
    }

    @Override
    protected void initViews(View view) {
        recyclerview = view.findViewById(R.id.recyclerview);
        VirtualLayoutManager manager = new VirtualLayoutManager(getContext());
        delegateAdapter = new DelegateAdapter(manager);
        recyclerview.setLayoutManager(manager);
        ordinaryEffectSwitchAdapter = new OrdinaryEffectSwitchAdapter(new LinearLayoutHelper(), getActivity());
        adapter = new OrdinaryAdapter(new GridLayoutHelper(4), getActivity());
        delegateAdapter.addAdapter(ordinaryEffectSwitchAdapter);
        delegateAdapter.addAdapter(adapter);
        recyclerview.setAdapter(delegateAdapter);
        initData();
        setListener();
    }

    private void setListener() {
        if (adapter == null) {
            Log.i(TAG, "adapter is null");
            return;
        }
        adapter.setOrdinaryListener(new OrdinaryAdapter.OrdinaryListener() {
            @Override
            public void click(int pos) {
                Log.i(TAG, "click");
                if (SoundEffectUtils.getOrdHwAudioEffectItems().size() == 0)
                    return;
                boolean isNotSet = false;
                for (int i = 0; i < adapter.getOrdinaryList().size(); i++) {
                    OrdinaryBean bean = adapter.getOrdinaryList().get(i);
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
                resetChooseEffect();
                adapter.notifyDataSetChanged();
                OrdinaryBean ordinaryBean = adapter.getOrdinaryList().get(pos);
                for (HwAudioEffectItem hwAudioEffectItem : SoundEffectUtils.getOrdHwAudioEffectItems()) {
                    if (SoundEffectConstant.ORDINARY_SOUNDEFFECT_CUSTOM_TYPE == hwAudioEffectItem.getType()) {
                        if (ordinaryBean.resId.equals(hwAudioEffectItem.getResId())) {
                            Log.i(TAG, "setEffect resId" + ordinaryBean.resId);
                            SoundEffectHelper.getInstance().setEffectSwitch(true);
                            PlayHelper.getInstance().setEffect(hwAudioEffectItem, new SoundEffectListener(pos));
                            break;
                        }
                    } else {
                        if (ordinaryBean.resId.equals(hwAudioEffectItem.getName())) {
                            Log.i(TAG, "setEffect resId" + ordinaryBean.resId);
                            SoundEffectHelper.getInstance().setEffectSwitch(true);
                            PlayHelper.getInstance().setEffect(hwAudioEffectItem, new SoundEffectListener(pos));
                            break;
                        }
                    }
                }
            }
        });
        ordinaryEffectSwitchAdapter.setSoundEffectSwitchOnClick(new OrdinaryEffectSwitchAdapter.SoundEffectSwitchOnClick() {
            @Override
            public void click(boolean open) {
                if (!open) {
                    adapter.reset();
                }
            }
        });
    }

    private void initData() {
        if (adapter == null) {
            Log.i(TAG, "adapter is null");
            return;
        }
        adapter.setOrdinaryList(SoundEffectUtils.getOrdinaryBeanList());
        SoundEffectHelper.getInstance().getOrdinarySoundEffect(new GetSoundEffectListCallback(new OrdinaryGetSoundEffectListListener()));
    }


    private void resetChooseEffect() {
        for(SoundEffectBean soundEffectBean : SoundEffectUtils.getSoundEffectBeanList()) {
            soundEffectBean.using = false;
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (adapter != null && isVisibleToUser) {
            Log.i(TAG, "setUserVisibleHint");
            adapter.getOrdinaryList().clear();
            initData();
        }
    }

    private class SoundEffectListener implements SetSoundEffectListener {

        private int pos;

        public SoundEffectListener(int pos) {
            this.pos = pos;
        }

        @Override
        public void onSuccess(int i) {
            Log.i(TAG, "onSuccess" + i);
            // sound effect set success
            if (i == 0) {
                adapter.setSelect(pos, true);
                SoundEffectHelper.getInstance().setSoundOrdinaryLightOn(true);
                ordinaryEffectSwitchAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(int i, String s) {
            Log.i(TAG, "onError" + i + ";" + s);
        }
    }

    class OrdinaryGetSoundEffectListListener implements GetSoundEffectListListener {
        @Override
        public void onSuccess(List<HwAudioEffectItem> list) {
            Log.i(TAG, "onSuccess list size == " + list.size());
            SoundEffectUtils.getOrdHwAudioEffectItems().clear();
            SoundEffectUtils.getOrdHwAudioEffectItems().addAll(list);
            ArrayList<OrdinaryBean> tempList = new ArrayList<>();
            for (HwAudioEffectItem hwAudioEffectItem : list) {
                if (hwAudioEffectItem == null || SoundEffectConstant.ORDINARY_SOUNDEFFECT_CUSTOM_TYPE != hwAudioEffectItem.getType()) {
                    continue;
                }
                OrdinaryBean ordinaryBean = new OrdinaryBean(hwAudioEffectItem.getName());
                ordinaryBean.resId = hwAudioEffectItem.getResId();
                ordinaryBean.type = hwAudioEffectItem.getType();
                tempList.add(ordinaryBean);
            }
            adapter.setOrdinaryList(tempList);
            HwAudioEffectItem hwAudioEffectItem = SoundEffectHelper.getInstance().getNowUsingSoundEffect();
            String resId = hwAudioEffectItem != null ? hwAudioEffectItem.getResId():"";
            for (OrdinaryBean ordinaryBean : adapter.getOrdinaryList()) {
                if (!TextUtils.isEmpty(resId) && hwAudioEffectItem.getResId().equals(ordinaryBean.resId)) {
                    ordinaryBean.using = true;
                    continue;
                }
                ordinaryBean.using = false;
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onError(int i, String s) {
            Log.i(TAG, "onError" + s + "; i ==" + i);
        }
    };

}
