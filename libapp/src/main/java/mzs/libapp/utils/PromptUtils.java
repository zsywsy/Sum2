package mzs.libapp.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.util.LinkedList;

import mzs.libapp.state.AppState;
import mzs.libtools.utils.depend.DebugUtils;

/**
 * Created by 24275 on 2016/9/19.
 */
public class PromptUtils {

    public static final boolean DefaultToastBackground = false;

    private static LinkedList<ToastInfo> toasts = new LinkedList<>();

    private static Handler handler = new Handler();

    private static boolean isToasting = false;

    public static void toast(Context context, String msg, boolean shortTime, boolean background) {
        toasts.add(new ToastInfo(msg, shortTime, background));
        if (!isToasting) {
            isToasting = true;
            startToast(context);
        }
    }

    public static void toast(Context context, String msg, boolean shortTime) {
        toast(context, msg, shortTime, DefaultToastBackground);
    }

    private static void startToast(Context context) {
        if (toasts.isEmpty()) {
            isToasting = false;
            return;
        }
        ToastInfo toastInfo = toasts.poll();
        if (AppState.getInstance().isAppRunningForeground() || toastInfo.isBackground()) {
            DebugUtils.log("toast");
            toast(context, toastInfo);
        } else {
            startToast(context);
        }

    }

    private static void toast(final Context context, final ToastInfo toastInfo) {
        int duration = toastInfo.isShortTime() ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;
        int delayTime = toastInfo.isShortTime() ? 2000 : 3500;
        Toast.makeText(context, toastInfo.getMsg(), duration).show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startToast(context);
            }
        }, delayTime);
    }

    static class ToastInfo {
        private String msg;
        private boolean shortTime;
        private boolean background;

        public ToastInfo() {
        }

        public ToastInfo(String msg, boolean shortTime, boolean background) {
            this.msg = msg;
            this.shortTime = shortTime;
            this.background = background;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isShortTime() {
            return shortTime;
        }

        public void setShortTime(boolean shortTime) {
            this.shortTime = shortTime;
        }

        public boolean isBackground() {
            return background;
        }

        public void setBackground(boolean background) {
            this.background = background;
        }
    }


}
