package mzs.libtools.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import mzs.libtools.utils.depend.DebugUtils;
import mzs.libtools.utils.NetWorkUtils;


/**
 * Created by 24275 on 2016/5/26.
 */
public class NetworkReceiver extends BroadcastReceiver {

    private NetworkChangedLsn networkChangedLsn;

    @Override
    public void onReceive(Context context, Intent intent) {
        DebugUtils.log("netRc:" + intent.getStringExtra("value") + ";ordered:" + isOrderedBroadcast() + ";sticky:" + isInitialStickyBroadcast());
        DebugUtils.log("isOnline:" + NetWorkUtils.isOnline(context) + ";MOB:" + NetWorkUtils.isMobConnected(context) + ";WIFI:" + NetWorkUtils.isWifiConnected(context) + ";type:" + NetWorkUtils.getNetworkType(context) + "mobType:" + NetWorkUtils.getMobType(context));
        if (networkChangedLsn != null && ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            networkChangedLsn.onNetworkChanged();
        }
    }

    public IntentFilter getFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        return filter;
    }

    public void setNetworkChangedLsn(NetworkChangedLsn networkChangedLsn) {
        this.networkChangedLsn = networkChangedLsn;
    }

    public interface NetworkChangedLsn {
        void onNetworkChanged();
    }


}
