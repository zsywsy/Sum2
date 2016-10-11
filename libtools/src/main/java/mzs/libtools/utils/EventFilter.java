package mzs.libtools.utils;

import java.util.HashMap;

/**
 * Created by 24275 on 2016/8/24.
 */
public class EventFilter {

    public static final long DEFAULT_PERIOD = 500;

    private static HashMap<String, Long> mMaps = new HashMap<>();

    private static long clickMs = 0;

    public static boolean isActive(String tag, long periodMs) {
        long nowMs = System.currentTimeMillis();
        if (!mMaps.containsKey(tag)) {
            mMaps.put(tag, nowMs);
            return true;
        }
        long preMs = mMaps.get(tag);
        if (nowMs - preMs >= periodMs) {
            mMaps.put(tag, nowMs);
            return true;
        }
        return false;
    }

    public static boolean isActive(String tag) {
        return isActive(tag, DEFAULT_PERIOD);
    }

    public static boolean isActive() {
        long nowMs = System.currentTimeMillis();
        if (nowMs - clickMs >= DEFAULT_PERIOD) {
            clickMs = nowMs;
            return true;
        }
        return false;
    }

}
