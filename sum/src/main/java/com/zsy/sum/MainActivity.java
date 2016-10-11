package com.zsy.sum;

import android.app.ActivityManager;
import android.content.Context;
import android.widget.GridView;

import com.zsy.sum.recycler.RecyclerActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.adapter.CommAdapter;
import mzs.libtools.utils.CtrlHelper;
import mzs.libtools.utils.depend.DebugUtils;

public class MainActivity extends BaseActivity {

    private final int BaseIndex = 0;

    private static final String[] Samples = {"RxJavaRetrofit", "Notification", "GcSave", "RecyclerView"};

    private CommAdapter<String> mainGvAdapter;


    @BindView(R.id.gv_main) GridView mainGv;

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
            case BaseIndex:
                CtrlHelper.act2Act(this, RxRetrofitActivity.class);
                break;
            case BaseIndex + 1:
                CtrlHelper.act2Act(this, NotificationActivity.class);
                break;
            case BaseIndex + 2:
                CtrlHelper.act2Act(this, GcSaveActivity.class);
                break;
            case BaseIndex + 3:
                CtrlHelper.act2Act(this, RecyclerActivity.class);
                break;

        }
    }


    private boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(
                Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

}
