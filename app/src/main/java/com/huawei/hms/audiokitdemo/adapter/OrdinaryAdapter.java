package com.huawei.hms.audiokitdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.huawei.hms.audiokitdemo.R;
import com.huawei.hms.audiokitdemo.bean.OrdinaryBean;
import com.huawei.hms.audiokitdemo.constant.SoundEffectConstant;
import com.huawei.hms.audiokitdemo.utils.ResUtils;

import java.util.ArrayList;
import java.util.List;

public class OrdinaryAdapter extends DelegateAdapter.Adapter<OrdinaryAdapter.OrdinaryViewHolder> {

    private GridLayoutHelper gridLayoutHelper;

    private Context context;

    private List<OrdinaryBean> ordinaryList = new ArrayList<>();

    public OrdinaryAdapter(GridLayoutHelper gridLayoutHelper, Context context) {
        this.gridLayoutHelper = gridLayoutHelper;
        this.context = context;
        gridLayoutHelper.setAutoExpand(false);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        gridLayoutHelper.setHGap(ResUtils.dp2Px(context, 8));
        gridLayoutHelper.setVGap(ResUtils.dp2Px(context, 8));
        gridLayoutHelper.setMarginTop(ResUtils.dp2Px(context, 24));
        gridLayoutHelper.setMarginRight(ResUtils.dp2Px(context, 24));
        gridLayoutHelper.setMarginLeft(ResUtils.dp2Px(context, 24));
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public OrdinaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdinaryViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.odinary_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdinaryViewHolder holder, final int position) {
        final OrdinaryBean ordinaryBean = ordinaryList.get(position);
        holder.tvName.setText(ordinaryBean.name);
        if (ordinaryBean.using) {
            holder.tvName.setBackground(context.getResources().getDrawable(R.drawable.ordinary_use_selected_shape));
            holder.tvName.setTextColor(Color.parseColor("#EBC78D"));
        } else {
            holder.tvName.setBackground(context.getResources().getDrawable(R.drawable.sound_chosen_item_shape));
            holder.tvName.setTextColor(Color.parseColor("#FFFFFF"));
        }
        holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ordinaryListener != null) {
                        ordinaryListener.click(position);
                    } }
        });
    }

    public interface OrdinaryListener {
        void click(int pos);
    }

    private OrdinaryListener ordinaryListener;

    public void setOrdinaryListener(OrdinaryListener ordinaryListener) {
        this.ordinaryListener = ordinaryListener;
    }

    public void setOrdinaryList(List<OrdinaryBean> ordinaryList) {
        this.ordinaryList.addAll(ordinaryList);
        notifyDataSetChanged();
    }

    public void reset() {
        for (OrdinaryBean ordinaryBean : ordinaryList) {
            ordinaryBean.using = false;
        }
        notifyDataSetChanged();
    }

    public void setSelect(int pos, boolean using) {
        ordinaryList.get(pos).using = using;
        notifyDataSetChanged();
    }


    public List<OrdinaryBean> getOrdinaryList() {
        return ordinaryList;
    }

    @Override
    public int getItemCount() {
        return ordinaryList.size();
    }

    class OrdinaryViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public OrdinaryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName= itemView.findViewById(R.id.tv_name);
        }
    }
}
