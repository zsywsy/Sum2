package mzs.libtools.utils;

/**
 * Created by 24275 on 2016/8/19.
 */
public class MemoryUtils {

    public static long MIN_MEMORY = 10 * 1024 * 1024;

    public static long getRestMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.maxMemory() - runtime.totalMemory() + runtime.freeMemory();
    }

    public static boolean isMemoryEnough() {
        return getRestMemory() > MIN_MEMORY;
    }

}
