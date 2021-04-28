package com.huawei.hms.audiokitdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.huawei.hms.audiokitdemo.R;
import com.huawei.hms.audiokitdemo.utils.ResUtils;

public class ChooseHeadAdapter extends DelegateAdapter.Adapter<ChooseHeadAdapter.SoundHeadViewHolder> {

    private LinearLayoutHelper linearLayoutHelper;

    private Context context;

    public ChooseHeadAdapter(LinearLayoutHelper linearLayoutHelper, Context context) {
        this.linearLayoutHelper = linearLayoutHelper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        linearLayoutHelper.setMarginTop(ResUtils.dp2Px(context, 24));
        return linearLayoutHelper;
    }

    @NonNull
    @Override
    public SoundHeadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SoundHeadViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_effect_chosen_head, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SoundHeadViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class SoundHeadViewHolder extends RecyclerView.ViewHolder {

        public SoundHeadViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
