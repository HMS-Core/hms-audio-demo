package com.huawei.hms.audiokitdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.huawei.hms.audiokitdemo.R;
import com.huawei.hms.audiokitdemo.bean.SoundEffectBean;
import com.huawei.hms.audiokitdemo.utils.ResUtils;

import java.util.ArrayList;
import java.util.List;

public class ChooseAdapter extends DelegateAdapter.Adapter<ChooseAdapter.SoundEffectViewHolder> {

    private LinearLayoutHelper linearLayoutHelper;

    private Context context;

    private List<SoundEffectBean> soundEffectBeans = new ArrayList<>();

    public ChooseAdapter(LinearLayoutHelper linearLayoutHelper, Context context) {
        this.linearLayoutHelper = linearLayoutHelper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        linearLayoutHelper.setMarginTop(ResUtils.dp2Px(context, 8));
        linearLayoutHelper.setMarginLeft(ResUtils.dp2Px(context, 24));
        linearLayoutHelper.setMarginRight(ResUtils.dp2Px(context, 24));
        return linearLayoutHelper;
    }

    @NonNull
    @Override
    public SoundEffectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SoundEffectViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.sound_effect_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SoundEffectViewHolder holder, final int position) {
        SoundEffectBean soundEffectBean = soundEffectBeans.get(position);
        holder.tvName.setText(soundEffectBean.soundEffectName);
        holder.tvDesc.setText(soundEffectBean.desc);
        holder.icon.setImageDrawable(context.getResources().getDrawable(soundEffectBean.icon));
        if (soundEffectBean.using) {
            holder.tUse.setText(ResUtils.getString(R.string.using));
            holder.tUse.setTextColor(Color.parseColor("#EBC78D"));
            holder.tUse.setBackground(context.getResources().getDrawable(R.drawable.sound_use_selected_shape));
        } else {
            holder.tUse.setText(ResUtils.getString(R.string.use));
            holder.tUse.setBackground(context.getResources().getDrawable(R.drawable.sound_use_default_shape));
            holder.tUse.setTextColor(Color.parseColor("#000000"));
        }
        holder.tUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseClickListener != null) {
                    chooseClickListener.click(position);
                }
            }
        });
    }

    public void reset() {
        for (SoundEffectBean soundEffectBean : soundEffectBeans) {
            soundEffectBean.using = false;
        }
        notifyDataSetChanged();
    }

    private ChooseClickListener chooseClickListener;

    public void setChooseClickListener(ChooseClickListener chooseClickListener) {
        this.chooseClickListener = chooseClickListener;
    }

    public interface ChooseClickListener {
        void click(int pos);
    }

    public void setSoundEffectBeans(List<SoundEffectBean> soundEffectBeans) {
        this.soundEffectBeans.addAll(soundEffectBeans);
        notifyDataSetChanged();
    }

    public List<SoundEffectBean> getSoundEffectBeans() {
        return soundEffectBeans;
    }


    public void setSelect(int pos, boolean using) {
        soundEffectBeans.get(pos).using = using;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return soundEffectBeans.size();
    }

    class SoundEffectViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView icon;

        TextView tvName;

        TextView tvDesc;

        TextView tUse;

        public SoundEffectViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tUse = itemView.findViewById(R.id.tv_use);
        }
    }
}
