package com.zsy.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import mzs.libtools.utils.depend.DebugUtils;

/**
 * Created by 24275 on 2016/9/26.
 */

public class SumReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DebugUtils.log("haha");
        DebugUtils.log(intent.getAction() + ";" + isOrderedBroadcast() + ";" + isInitialStickyBroadcast());
    }
}
