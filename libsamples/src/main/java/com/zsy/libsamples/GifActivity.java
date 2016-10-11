package com.zsy.libsamples;

import android.os.Handler;
import android.widget.ListView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.adapter.CommAdapter;
import mzs.libtools.utils.PromptUtils;
import mzs.libtools.utils.depend.DebugUtils;
import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by 24275 on 2016/9/20.
 */

public class GifActivity extends BaseActivity {


    private final String[] Samples = {"set", "start", "stop", "log", "reset", "setSpeed", "seekTo"};
    private CommAdapter<String> adapter;
    private GifDrawable gifDrawable;
    private Handler handler = new Handler();

    @BindView(R.id.giv) GifImageView giv;
    @BindView(R.id.lv_gif) ListView gifLv;

    @Override
    public void initUI() {
        setContentView(R.layout.activity_gif);
    }

    @Override
    public void initData() {
        try {
            gifDrawable = new GifDrawable(getResources(), R.drawable.self);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> data = Arrays.asList(Samples);
        adapter = new CommAdapter<String>(data, R.layout.item_samples) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_samples, obj);
            }
        };
    }

    @Override
    public void initView() {
        gifLv.setAdapter(adapter);
    }

    @OnItemClick(R.id.lv_gif)
    @Override
    public void doItemClick(int position) {
        switch (position) {
            case 0:
                gifDrawable.setLoopCount(1);
                giv.setImageDrawable(gifDrawable);
                if(gifDrawable.isRunning())
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PromptUtils.toast(GifActivity.this, "gif compelete");
                    }
                }, gifDrawable.getDuration());
                break;
            case 1:
                gifDrawable.start();
                break;
            case 2:
                gifDrawable.stop();
                break;
            case 3:
                DebugUtils.log("duration:" + gifDrawable.getDuration() + "position:" + gifDrawable.getCurrentPosition());
                break;
            case 4:
                gifDrawable.reset();
                break;
            case 5:
                gifDrawable.setSpeed(2);
                break;
            case 6:
                gifDrawable.seekTo(20);
                break;
        }
    }
}
