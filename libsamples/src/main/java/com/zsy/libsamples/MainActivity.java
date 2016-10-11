package com.zsy.libsamples;

import android.os.Bundle;
import android.widget.GridView;

import com.zsy.libsamples.TabLayout.TabLayoutActivity;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnItemClick;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.adapter.CommAdapter;
import mzs.libtools.utils.CtrlHelper;
import mzs.libtools.utils.depend.DebugUtils;
import mzs.libui.view.TabLayout;
import mzs.libui.view.TimerView;

public class MainActivity extends BaseActivity {

    private static final String[] Samples = {"net&battery", "gif", "tabLayout", "timerView", "threadCount"};

    private CommAdapter<String> mainGvAdapter;

    private boolean setDefaultSelect = false;

    @BindView(R.id.gv_main) GridView mainGv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void initUI() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        List<String> datas = Arrays.asList(Samples);
        mainGvAdapter = new CommAdapter<String>(datas, R.layout.item_samples) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_samples, obj);
            }
        };
    }

    @Override
    public void initView() {
        mainGv.setAdapter(mainGvAdapter);
    }

    @OnItemClick(R.id.gv_main)
    @Override
    public void doItemClick(int position) {
        DebugUtils.log("position:" + position);
        switch (position) {
            case 0:
                CtrlHelper.act2Act(this, NetBatteryActivity.class);
                break;
            case 1:
                CtrlHelper.act2Act(this, GifActivity.class);
                break;
            case 2:
                CtrlHelper.act2Act(this, TabLayoutActivity.class);
                break;
            case 3:
                CtrlHelper.act2Act(this, TimerActivity.class);
                break;
            case 4:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                DebugUtils.log("threadCount:" + getCount());
                break;
        }
    }

    public static int getCount() {
        return Thread.currentThread().getThreadGroup().activeCount();
    }

}
