package com.zsy.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mzs.libapp.api.ClickLsn;
import mzs.libtools.utils.depend.DebugUtils;

/**
 * Created by 24275 on 2016/9/27.
 */

public class ClickLsnAdapter extends RecyclerView.Adapter<ClickLsnAdapter.ViewHolder> {

    private List<Info> mData;

    public ClickLsnAdapter(List<Info> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resId;
        if (viewType == 0) {
            resId = R.layout.item_rv_click0;
        } else {
            resId = R.layout.item_rv_click;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.btn.setText(mData.get(position).getBtnText());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DebugUtils.log("btn:" + position);
            }
        });
        holder.tv.setText(mData.get(position).getTitleText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DebugUtils.log("itemView;" + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        @BindView(R.id.btn) Button btn;
        @BindView(R.id.tv) TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
