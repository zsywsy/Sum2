package mzs.libtools.utils;

import android.text.Html;

/**
 * Created by 24275 on 2016/8/17.
 */
public class HtmlUtills {

    public static CharSequence getColorText(CharSequence text, String color) {
        return "<font color='" + color + "'>" + text + "</font>";
    }

}
