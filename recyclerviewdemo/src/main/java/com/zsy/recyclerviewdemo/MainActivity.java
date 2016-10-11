package com.zsy.recyclerviewdemo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.LinkedList;

import butterknife.BindView;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.utils.depend.DebugUtils;
import mzs.libtools.utils.res.Res;

import static android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.view.View.SCROLL_AXIS_HORIZONTAL;

public class MainActivity extends BaseActivity {


    private MyAdapter adapter;
    private LinkedList<String> data;

    private boolean isLoading = true;

    private String url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=374245941,14045788&fm=116&gp=0.jpg";
    private String url2 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1590736614,1368131788&fm=116&gp=0.jpg";

    @BindView(R.id.rv) RecyclerView rv;
//    @BindView(R.id.iv) ImageView iv;

    @Override
    public void initUI() {
        setContentView(R.layout.activity_main);
    }


    @Override
    public void initData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            data.add("test");
//            adapter.notifyDataSetChanged();
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void initView() {
        data = new LinkedList<>();
        for (int i = 0; i < 80; i++) {
            data.add(Res.imageThumbUrls[i]);
        }
        adapter = new MyAdapter(this, data);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
//        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                DebugUtils.log("state:" + newState);
//                if (newState == SCROLL_STATE_SETTLING && isLoading) {
//                    Glide.with(MainActivity.this).pauseRequests();
//                    isLoading = false;
//                } else if (newState != SCROLL_STATE_SETTLING && !isLoading) {
//                    Glide.with(MainActivity.this).resumeRequests();
//                    isLoading = true;
//                }
//
//            }
//        });

//        LinkedList<Info> data = new LinkedList<>();
//        for (int i = 0; i < 80; i++) {
//            data.add(new Info("title:" + i, "click:" + i));
//        }
//
//        ClickLsnAdapter adapter = new ClickLsnAdapter(data);
//        rv.setLayoutManager(new LinearLayoutManager(this));
//        rv.setAdapter(adapter);

    }
}
