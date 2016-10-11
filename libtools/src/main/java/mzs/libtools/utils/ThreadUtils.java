package mzs.libtools.utils;

import android.os.Looper;

/**
 * Created by 24275 on 2016/9/29.
 */

public class ThreadUtils {

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static int getThreadCount(){
        return Thread.currentThread().getThreadGroup().activeCount();
    }

}
