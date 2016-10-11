package mzs.libtools.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import mzs.libtools.utils.depend.DebugUtils;

/**
 * Created by 24275 on 2016/5/27.
 */
public class BatteryUtils {

    public static Intent getBatteryIntent(Context context) {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        return context.registerReceiver(null, ifilter);
    }

    public static int getScale(Intent intent) {
        return intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
    }

    public static int getLevel(Intent intent) {
        return intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
    }

    public static int getChargeStatus(Intent intent) {
        return intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
    }

    public static boolean isCharging(int status) {
        return status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
    }

    public static boolean isCharging(Intent intent) {
        int status = getChargeStatus(intent);
        return isCharging(status);
    }

    public static void test(Context context) {
        Intent intent = BatteryUtils.getBatteryIntent(context);
        DebugUtils.log("isCharging:" + BatteryUtils.isCharging(intent) + ";level:" + BatteryUtils.getLevel(intent) + ";Scale:" + BatteryUtils.getScale(intent));
    }

}
