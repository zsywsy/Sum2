package com.zsy.recyclerviewdemo;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;

import java.util.List;

import mzs.libtools.utils.depend.DebugUtils;

/**
 * Created by 24275 on 2016/9/23.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHodler> {

    private Context context;
    private List<String> datas;

    private ImageView iv;

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public MyAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
        iv = new ImageView(context);
    }


    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_rv_iv, parent, false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHodler holder, final int position) {
//        Glide.with(holder.itemView.getContext()).load(datas.get(position + 1)).override(100, 100).into(iv);
        Glide.with(holder.itemView.getContext()).load(datas.get(position)).
                    placeholder(R.mipmap.dft).
                    error(R.mipmap.error).dontAnimate().
                    //                crossFade(2000).
                            into(holder.iv);
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
