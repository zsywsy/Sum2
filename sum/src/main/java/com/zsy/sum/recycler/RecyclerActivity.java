package com.zsy.sum.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.zsy.sum.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 24275 on 2016/9/23.
 */

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initData();
        initRecyclerView();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add("Item " + i);
        }
    }

    private void initRecyclerView() {
        //1 实例化RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);//new GridLayoutManager(this,2);//new LinearLayoutManager(this);
        //2 为RecyclerView创建布局管理器，这里使用的是LinearLayoutManager，表示里面的Item排列是线性排列
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(mData);
        //3 设置数据适配器
        mRecyclerView.setAdapter(mAdapter);
    }
}