package com.zsy.libsamples;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.zsy.libsamples.R;

import java.util.Locale;

import butterknife.BindView;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.receiver.BatteryReceiver;
import mzs.libtools.receiver.NetworkReceiver;
import mzs.libtools.utils.NetWorkUtils;

public class NetBatteryActivity extends BaseActivity {

    private NetworkReceiver networkReceiver;
    private Context context;

    @BindView(R.id.tv_net) TextView netTv;
    @BindView(R.id.tv_battery) TextView batteryTv;

    @Override
    public void initUI() {
        setContentView(R.layout.activity_net_battery);
    }

    @Override
    public void initData() {
        context = this;
        BatteryReceiver.setBatteryLsn(new BatteryReceiver.BatteryLsn() {
            @Override
            public void onBatteryChanged(Intent intent) {
                int level = intent.getIntExtra("level", -1);
                int scale = intent.getIntExtra("scale", -1);
                batteryTv.setText(String.format(Locale.ENGLISH, "%d/%d", level, scale));
            }
        });
        BatteryReceiver.regist(this);

        networkReceiver = new NetworkReceiver();
        networkReceiver.setNetworkChangedLsn(new NetworkReceiver.NetworkChangedLsn() {
            @Override
            public void onNetworkChanged() {
                netTv.setText(String.format(Locale.ENGLISH, "isOnline:%s;MOB:%s;WIFI:%s;type:%dmobType:%d", NetWorkUtils.isOnline(context), NetWorkUtils.isMobConnected(context), NetWorkUtils.isWifiConnected(context), NetWorkUtils.getNetworkType(context), NetWorkUtils.getMobType(context)));
            }
        });
        registerReceiver(networkReceiver, networkReceiver.getFilter());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BatteryReceiver.unRegist(this);
        unregisterReceiver(networkReceiver);

    }
}
