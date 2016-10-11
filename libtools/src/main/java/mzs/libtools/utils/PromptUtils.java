package mzs.libtools.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 24275 on 2016/8/17.
 */
public class PromptUtils {

    public static void toast(Context context, String msg, int duration) {
        Toast.makeText(context, msg, duration).show();
    }

    public static void toast(Context context, String msg) {
        toast(context, msg, Toast.LENGTH_SHORT);
    }

}
