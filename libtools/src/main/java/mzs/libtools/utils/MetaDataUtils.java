package mzs.libtools.utils;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;

/**
 * Created by 24275 on 2016/5/31.
 */
public class MetaDataUtils {

    public static ApplicationInfo getAppInfo(Context context) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appInfo;
    }

    public static ActivityInfo getActivityInfo(Activity activity) {
        ActivityInfo activityInfo = null;
        try {
            activityInfo = activity.getPackageManager().getActivityInfo(activity.getComponentName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return activityInfo;
    }

    public static ServiceInfo getServiceInfo(Service service) {
        ServiceInfo serviceInfo = null;
        ComponentName componentName = new ComponentName(service, service.getClass());
        try {
            serviceInfo = service.getPackageManager().getServiceInfo(componentName, PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return serviceInfo;
    }

    public static ActivityInfo getReceiverInfo(Context context, Class<BroadcastReceiver> cls) {
        ActivityInfo activityInfo = null;
        ComponentName componentName = new ComponentName(context, cls);
        try {
            activityInfo = context.getPackageManager().getActivityInfo(componentName, PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return activityInfo;
    }

    public static String getMetaData(PackageItemInfo packageItemInfo, String key) {
        if (packageItemInfo == null) {
            throw new NullPointerException("ApplicationInfo null");
        }
        return packageItemInfo.metaData.getString(key, null);
    }

}
