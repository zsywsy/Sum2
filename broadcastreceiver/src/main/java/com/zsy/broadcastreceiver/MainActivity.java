package com.zsy.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.utils.depend.DebugUtils;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn1) Button btn1;
    @BindView(R.id.btn2) Button btn2;
    SumReceiver sumReceiver;

    @Override
    public void initUI() {
        setContentView(R.layout.activity_main);
        DebugUtils.log("initUi");
//        sumReceiver = new SumReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        registerReceiver(sumReceiver, intentFilter);
    }

    @OnClick({R.id.btn1, R.id.btn2})
    @Override
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:

                regist1();
                break;
            case R.id.btn2:
                regist2();
                break;
        }
    }

    private void regist1() {
        Receiver1 receiver1 = new Receiver1();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.zsy.broadcastsender.test");
        registerReceiver(receiver1, intentFilter);
    }

    private void regist2() {
        Receiver2 receiver2 = new Receiver2();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.zsy.broadcastsender.test");
        registerReceiver(receiver2, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(sumReceiver);
    }
}

class Receiver1 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isOrderedBroadcast()) {
            abortBroadcast();
        }
        DebugUtils.log("r1:" + intent.getStringExtra("value") + ";ordered:" + isOrderedBroadcast() + ";sticky:" + isInitialStickyBroadcast());
    }


}


class Receiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isOrderedBroadcast()) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        DebugUtils.log("lanjie");
                        abortBroadcast();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        DebugUtils.log("r2:" + intent.getStringExtra("value") + ";ordered:" + isOrderedBroadcast() + ";sticky:" + isInitialStickyBroadcast());
    }
}