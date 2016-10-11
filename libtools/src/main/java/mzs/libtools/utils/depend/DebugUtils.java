package mzs.libtools.utils.depend;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by 24275 on 2016/5/24.
 */
public class DebugUtils {

    public static final String LOG_TAG = "logDebug";
    public static boolean DEBUG = true;

    private static String tag = LOG_TAG;
    private static int type = 1; // 0->mine;1->Logger;

    public static void logInit(int type, String tag) {
        if (!DEBUG) {
            return;
        }
        DebugUtils.type = type;
        DebugUtils.tag = tag;
        if (type == 1) {
            Logger.init(tag).methodCount(1).methodOffset(2);
        }
    }

    public static void logInit(int type) {
        logInit(type, LOG_TAG);
    }

    public static void logInit() {
        logInit(type);
    }

    public static void log(int type, String tag, String msg) {
        if (!DEBUG) {
            return;
        }
        if (msg == null) {
            msg = "null";
        }
        if (type == 0) {
            Log.d(tag, msg);
        } else {
            int offset = LOG_TAG.equals(tag) ? 3 : 2;
            Logger.init(tag).methodCount(1).methodOffset(offset);
            Logger.d(msg);
        }
    }

    public static void log(String tag, String msg) {
        log(type, tag, msg);
    }

    public static void log(String msg) {
        log(tag, msg);
    }

//    public static void log(String msg, Object o) {
//        if (!DEBUG) {
//            return;
//        }
//        if (msg == null) {
//            msg = "null";
//        }
//        if (o == null) {
//            Log.i(tag, msg);
//        } else {
//            String prefix;
//            if (o instanceof String) {
//                prefix = o.toString();
//            } else {
//                prefix = o.getClass().getName();
//            }
//            Log.i(tag, prefix + ":" + msg);
//        }
//    }
//
//    public static void log(String msg) {
//        log(msg, null);
//    }
//
//    public static void log(Object o) {
//        if (o == null) {
//            log("null");
//        } else {
//            log(o.toString());
//        }
//    }

    public static void toast(Context context, String msg) {
        if (DEBUG) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showOnView(TextView tv, String msg, boolean append) {
        if (append) {
            tv.append(msg);
        } else {
            tv.setText(msg);
        }
    }


    //    it's logger method below

//
//
//    public static void logI(String info, String tag) {
//        if (DEBUG) {
//            if (tag == null) {
//                tag = LOG_TAG;
//            }
//            Logger.t(tag).i(info);
//        }
//    }
//
//
//    private static void logI(String info) {
//        logI(info, null);
//    }
//
//    private void logJson(String json, String tag) {
//        if (DEBUG) {
//            if (tag == null) {
//                tag = LOG_TAG;
//            }
//            Logger.t(tag).json(json);
//        }
//    }
//
//    private void logJson(String json) {
//        logJson(json, null);
//    }

}
