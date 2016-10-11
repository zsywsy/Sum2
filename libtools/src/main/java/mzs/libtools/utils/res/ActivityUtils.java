package mzs.libtools.utils.res;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * Created by 24275 on 2016/8/26.
 */
public class ActivityUtils {

    public static boolean isActivityExist(Context context, String pkg, String cls) {
        if (context == null) {
            throw new NullPointerException("context == null");
        }
        if (TextUtils.isEmpty(pkg) || TextUtils.isEmpty(cls)) {
            return false;
        }
        Intent intent = new Intent();
        intent.setClassName(pkg, cls);
        return context.getPackageManager().resolveActivity(intent, 0) != null;
    }

    public static void startActivity(Context context, String pkg, String cls) {
        if (context == null) {
            throw new NullPointerException("context == null");
        }
        if (TextUtils.isEmpty(pkg) || TextUtils.isEmpty(cls)) {
            return;
        }
        ComponentName componentName = new ComponentName(pkg, cls);
        Intent intent = new Intent();
        intent.putExtra("name", "mao");
        intent.setComponent(componentName);
        context.startActivity(intent);
    }

    public static void startActivityIfExist(Context context, String pkg, String cls) {
        if (context == null) {
            throw new NullPointerException("context == null");
        }
        if (isActivityExist(context, pkg, cls)) {
            startActivity(context, pkg, cls);
        }
    }

}
