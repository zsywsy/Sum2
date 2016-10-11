package mzs.libtools.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Administrator on 15-10-23.
 */
public class NetWorkUtils {

    /**
     * need permission ACCESS_NETWORK_STATE
     */
    private static ConnectivityManager connManager;
    private static Object connLock = new Object();

    /**
     * need permission READ_PHONE_STATE
     */
    private static TelephonyManager teleManager;
    private static Object teleLock = new Object();

    public static ConnectivityManager getConnMgr(Context context) {
        if (connManager == null) {
            synchronized (connLock) {
                if (connManager == null) {
                    connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                }
            }
        }
        return connManager;
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        return getConnMgr(context).getActiveNetworkInfo();
    }

    public static boolean isOnline(NetworkInfo networkInfo) {
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static boolean isOnline(Context context) {
        return isOnline(getNetworkInfo(context));
    }

    /**
     * @param context
     * @return -1 not connected;0 mob;1 wifi
     */
    public static int getNetworkType(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        if (!isOnline(networkInfo)) {
            return -1;
        }
        return networkInfo.getType();

    }

    public static boolean isTypeConnected(Context context, int type) {
        return type == getNetworkType(context);
    }

    public static boolean isWifiConnected(Context context) {
        return isTypeConnected(context, ConnectivityManager.TYPE_WIFI);
    }

    public static boolean isMobConnected(Context context) {
        return isTypeConnected(context, ConnectivityManager.TYPE_MOBILE);
    }

    public static TelephonyManager getTeleManager(Context context) {
        if (teleManager == null) {
            synchronized (teleLock) {
                if (teleManager == null) {
                    teleManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                }
            }
        }
        return teleManager;
    }

    /**
     * @param context
     * @return 0 2g;1 3g;2 4g
     */
    public static int getMobType(Context context) {
        int type = getTeleManager(context).getNetworkType();
        return getNetworkClass(type);
    }

    /**
     * @param networkType
     * @return 0 2g;1 3g;2 4g
     */
    public static int getNetworkClass(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case 16:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return 0;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
            case 17:
                return 1;
            case TelephonyManager.NETWORK_TYPE_LTE:
            case 18:
                return 2;
            default:
                return -1;
        }
    }

    /**
     * @param context
     * @return -1 unknown;0 移动;1 联通;2 电信
     */
    public static int getProvider(Context context) {
        int provider = -1;
        TelephonyManager telephonyManager = getTeleManager(context);
        String IMSI = telephonyManager.getSubscriberId();
        if (IMSI == null) {
            if (TelephonyManager.SIM_STATE_READY == telephonyManager
                    .getSimState()) {
                String operator = telephonyManager.getSimOperator();
                if (operator != null) {
                    if (operator.equals("46000")
                            || operator.equals("46002")
                            || operator.equals("46007")) {
//                        provider = "中国移动";
                        provider = 0;
                    } else if (operator.equals("46001")) {
//                        provider = "中国联通";
                        provider = 1;
                    } else if (operator.equals("46003")) {
//                        provider = "中国电信";
                        provider = 2;
                    }
                }
            }
        } else {
            if (IMSI.startsWith("46000") || IMSI.startsWith("46002")
                    || IMSI.startsWith("46007")) {
//                provider = "中国移动";
                provider = 0;
            } else if (IMSI.startsWith("46001")) {
//                provider = "中国联通";
                provider = 1;
            } else if (IMSI.startsWith("46003")) {
//                provider = "中国电信";
                provider = 2;
            }
        }
        return provider;
    }

}
