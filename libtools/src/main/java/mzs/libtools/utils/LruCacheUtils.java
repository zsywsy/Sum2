package mzs.libtools.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

public class LruCacheUtils {

    private static LruCacheUtils instance;

    private LruCacheUtils() {
    }

    public static LruCacheUtils getInstance() {
        if (instance == null) {
            synchronized (LruCacheUtils.class) {
                if (instance == null) {
                    instance = new LruCacheUtils();
                }
            }
        }
        return instance;
    }

    private LruCache<String, Bitmap> mMemoryCache;

    public void init() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    public int getCacheSize() {
        return mMemoryCache.size();
    }

    public int getCount() {
        return mMemoryCache.putCount() - mMemoryCache.evictionCount();
    }

}
