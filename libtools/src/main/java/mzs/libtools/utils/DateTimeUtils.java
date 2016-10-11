package mzs.libtools.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtils {

    public static final String DEFAULT_TIME_ZONE = "GMT+08:00";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_NAME_FORMAT = "yyyy-MM-dd_HH-mm-ss";

    public static SimpleDateFormat getSDF(String format, String timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (timeZone != null) {
            sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        }
        return sdf;
    }

    public static SimpleDateFormat getDefaultSDF(String format) {
        return getSDF(format, null);
    }

    public static String getDateStr(String format, String timeZone, Object o) {
        return getSDF(format, timeZone).format(o);
    }

    public static String getDefaultDateStr(String format, Object o) {
        return getDefaultSDF(format).format(o);
    }

    public static String getNowStr(String format) {
        return getDefaultSDF(format).format(new Date());
    }

}
