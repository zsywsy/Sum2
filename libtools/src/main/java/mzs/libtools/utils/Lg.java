package mzs.libtools.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * Created by 24275 on 2016/9/29.
 */

public class Lg {

    public static final int LogType = 0; //android util
    public static final int LoggerType = 1; //logger
    private static final boolean Debug = true;

    private static final String Tag = "logTag";

    private static int type = LogType;
    private static String tag = Tag;

    private static final int MethodCount = 1;
    private static final int MethodOffset = 2;


    public static void init(int type, String tag) {
        Lg.tag = tag;
        Lg.type = type;
    }

    public static void i(int type, String tag, String msg) {
        if (!Debug) {
            return;
        }
        if (msg == null) {
            msg = "null";
        }
        if (tag == null) {
            tag = Tag;
        }
        if (type == LogType) {
            Log.i(tag, msg);
        } else {
            loggerI(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (type == LogType) {
            i(type, tag, msg);
        } else {
            loggerI(tag, msg);
        }
    }

    public static void i(String msg) {
        if (type == LogType) {
            i(tag, msg);
        } else {
            loggerI(tag, msg);
        }
    }

    private static void loggerI(String tag, String msg) {
        Logger.init(tag).methodOffset(MethodOffset).methodCount(MethodCount);
        Logger.i(msg);
    }

}
