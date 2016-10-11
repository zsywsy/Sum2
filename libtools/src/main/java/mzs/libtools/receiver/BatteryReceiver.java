package mzs.libtools.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import mzs.libtools.utils.depend.DebugUtils;
import mzs.libtools.utils.IterateIntent;

/**
 * Created by 24275 on 2016/5/26.
 */
public class BatteryReceiver extends BroadcastReceiver {

    private static BatteryReceiver receiver;

    private static BatteryLsn batteryLsn;

    @Override
    public void onReceive(Context context, Intent intent) {
        DebugUtils.log("btRc:" + intent.getStringExtra("value") + ";ordered:" + isOrderedBroadcast() + ";sticky:" + isInitialStickyBroadcast());
        String action = intent.getAction();
        DebugUtils.log(action);
        if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
        } else if (Intent.ACTION_BATTERY_OKAY.equals(action)) {
        } else if (Intent.ACTION_BATTERY_LOW.equals(action)) {
        } else if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
            if (batteryLsn != null) {
                batteryLsn.onBatteryChanged(intent);
            }
        }

        DebugUtils.log(IterateIntent.iterateIntent(intent, new StringBuilder()).toString());
    }

    public static BatteryReceiver regist(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        receiver = new BatteryReceiver();
        context.registerReceiver(receiver, filter);
        return receiver;
    }

    public static void unRegist(Context context) {
        context.unregisterReceiver(receiver);
    }


    public static void setBatteryLsn(BatteryLsn batteryLsn) {
        BatteryReceiver.batteryLsn = batteryLsn;
    }

    public static interface BatteryLsn {
        void onBatteryChanged(Intent intent);
    }

}
