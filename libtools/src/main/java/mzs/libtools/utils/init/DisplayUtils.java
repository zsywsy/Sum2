package mzs.libtools.utils.init;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * The class should init first
 */
public class DisplayUtils {

    private static DisplayUtils instance;
    private DisplayMetrics displayMetrics;

    private DisplayUtils() {
    }

    public static DisplayUtils getInstance() {
        if (instance == null) {
            synchronized (DisplayUtils.class) {
                if (instance == null) {
                    instance = new DisplayUtils();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        if (displayMetrics == null) {
            displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        }
    }

    public int dp2px(int dpValue) {
        return (int) (dpValue * displayMetrics.density + 0.5f);
    }

    public int px2dp(int dpValue) {
        return (int) (dpValue / displayMetrics.density + 0.5f);
    }

//    public DisplayMetrics getDisplayMetrics(Context context) {
//        if (displayMetrics == null) {
//            init(context);
//        }
//        return displayMetrics;
//    }


    public DisplayMetrics getDisplayMetrics() {
        return displayMetrics;
    }

}
