package com.zsy.recyclerviewdemo;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 24275 on 2016/9/23.
 */

class MyViewHodler extends RecyclerView.ViewHolder {

    View itemView;
    ImageView iv;

    MyViewHodler(View itemView) {
        super(itemView);
        this.itemView = itemView;
        iv = (ImageView) itemView.findViewById(R.id.iv_item_rv);
    }
}
