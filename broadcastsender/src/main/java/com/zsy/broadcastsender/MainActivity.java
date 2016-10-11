package com.zsy.broadcastsender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.utils.depend.DebugUtils;

public class MainActivity extends BaseActivity {


    @BindView(R.id.btn_normal) Button normalBtn;
    @BindView(R.id.btn_ordered) Button orderedBtn;
    @BindView(R.id.btn_sticky) Button stickyBtn;
    @BindView(R.id.btn_local) Button localBtn;
    @BindView(R.id.btn_regist) Button registBtn;

    @Override
    public void initUI() {
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.btn_normal)
    void sendNormal() {
        Intent intent = new Intent("com.zsy.broadcastsender.test");
        intent.putExtra("value", "normal");
        sendBroadcast(intent);
    }

    @OnClick(R.id.btn_ordered)
    void sendOrdered() {
        Intent intent = new Intent("com.zsy.broadcastsender.test");
        intent.putExtra("value", "ordered");
        sendOrderedBroadcast(intent, null);
    }

    @OnClick(R.id.btn_sticky)
    void sendSticky() {
        Intent intent = new Intent("com.zsy.broadcastsender.test");
        intent.putExtra("value", "sticky");
        sendStickyBroadcast(intent);
    }

    @OnClick(R.id.btn_local)
    void sendLocal() {
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        Intent intent = new Intent("com.zsy.broadcastsender.test");
        intent.putExtra("value", "local");
        manager.sendBroadcast(intent);
    }

    @OnClick(R.id.btn_regist)
    void regist() {
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        Receiver receiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.zsy.broadcastsender.test");
        manager.registerReceiver(receiver, intentFilter);

    }


}

class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        DebugUtils.log("local:" + intent.getStringExtra("value") + ";ordered:" + isOrderedBroadcast() + ";sticky:" + isInitialStickyBroadcast());
    }
}