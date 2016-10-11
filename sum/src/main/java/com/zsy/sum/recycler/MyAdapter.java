package com.zsy.sum.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsy.sum.R;

import java.util.List;
import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> mDataSet;
    int[] resIds = {R.mipmap.p1, R.mipmap.p2, R.mipmap.p3, R.mipmap.p4, R.mipmap.p5};

    //构造器，接受数据集
    public MyAdapter(List<String> data) {
        mDataSet = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局文件
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //将数据填充到具体的view中
//        holder.mTextView.setText(mDataSet.get(position));
        holder.iv.setImageResource(resIds[new Random().nextInt(4)]);
    }

    @Override
    public int getItemCount() {
//        return mDataSet.size();
        return 31;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            //由于itemView是item的布局文件，我们需要的是里面的textView，因此利用itemView.findViewById获      
            //取里面的textView实例，后面通过onBindViewHolder方法能直接填充数据到每一个textView了
//            mTextView = (TextView) itemView.findViewById(R.id.num);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}