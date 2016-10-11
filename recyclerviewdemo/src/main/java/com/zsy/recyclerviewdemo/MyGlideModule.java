package com.zsy.recyclerviewdemo;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by 24275 on 2016/9/26.
 */

public class MyGlideModule implements GlideModule {

    private final int InternalSize = 10 * 1024 * 1024;
    private final int ExternalSize = 100 * 1024 * 1024;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);

        builder.setMemoryCache(new LruResourceCache(50 * 1024));
        builder.setBitmapPool(new LruBitmapPool(100 * 1024));

//        DebugUtils.log(calculator.getBitmapPoolSize() + ";" + calculator.getMemoryCacheSize());
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, InternalSize));
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, ExternalSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
