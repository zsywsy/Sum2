package mzs.libtools.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.util.UUID;

/**
 * Created by 24275 on 2016/7/5.
 */
public class DeviceInfoUtils {

    public static String getUniqueId(Context context) {
        String join = StringUtils.joinIfNotNull(getAndroidId(context), getDeviceId(context), getWifiMac(context), getBtMac(), getHDID());
        return UUID.nameUUIDFromBytes(join.getBytes()).toString();
    }

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceId(Context context) {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    public static String getWifiMac(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wm.getConnectionInfo().getMacAddress();
    }

    public static String getBtMac() {
        BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
        m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return m_BluetoothAdapter.getAddress();
    }

    public static String getHDID() {
        StringBuilder sb = new StringBuilder();
        sb.append(Build.BOARD.length() % 10);
        sb.append(Build.BRAND.length() % 10);
        sb.append(getCPU_ABI().length() % 10);
        sb.append(Build.DEVICE.length() % 10);
        sb.append(Build.DISPLAY.length() % 10);
        sb.append(Build.HOST.length() % 10);
        sb.append(Build.ID.length() % 10);
        sb.append(Build.MANUFACTURER.length() % 10);
        sb.append(Build.MODEL.length() % 10);
        sb.append(Build.PRODUCT.length() % 10);
        sb.append(Build.TAGS.length() % 10);
        sb.append(Build.TYPE.length() % 10);
        sb.append(Build.USER.length() % 10);
        return sb.toString();
    }

    public static String getCPU_ABI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Build.SUPPORTED_ABIS[0];
        } else {
            return Build.CPU_ABI;
        }
    }

}


//class DeviceUuidFactory {
//
//    private final String ERROR_ANDROID_ID = "9774d56d682e549c";
//    private final String PREFS_FILE = "device_id.xml";
//    private final String PREFS_DEVICE_ID = "device_id";
//
//    private static UUID uuid;
//
//
//    public DeviceUuidFactory(Context context) {
//        if (uuid == null) {
//            synchronized (DeviceUuidFactory.class) {
//                if (uuid == null) {
//                    final SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
//                    final String id = prefs.getString(PREFS_DEVICE_ID, null);
//                    if (id != null) {
//                        // Use the ids previously computed and stored in the prefs file
//                        uuid = UUID.fromString(id);
//                    } else {
//                        final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//                        try {
//                            if (!ERROR_ANDROID_ID.equals(androidId)) {
//                                uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
//                            } else {
//                                final String deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
//                                uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")) : UUID.randomUUID();
//                            }
//                        } catch (UnsupportedEncodingException e) {
//                            throw new RuntimeException(e);
//                        }
//
//                        // Write the value out to the prefs file
//                        prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).apply();
//
//                    }
//
//                }
//            }
//        }
//
//    }
//
//
//    public UUID getDeviceUuid() {
//        return uuid;
//    }
//}