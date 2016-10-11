package com.zsy.sum.utils;

import com.zsy.sum.Const.ConstHttp;

import mzs.libtools.utils.depend.DebugUtils;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtils {

    private static final Converter.Factory gsonFactory = GsonConverterFactory.create();
    private static final Converter.Factory scalarsFactory = ScalarsConverterFactory.create();

    private static final CallAdapter.Factory rxJavaFactory = RxJavaCallAdapterFactory.create();

    private static Retrofit blogRetrofit;

    public static Retrofit getBlogRetrofit() {
        if (blogRetrofit == null) {
            synchronized (RetrofitUtils.class) {
                if (blogRetrofit == null) {
                    blogRetrofit = new Retrofit.Builder()
                            .baseUrl(ConstHttp.BaseUrl)
                            .addCallAdapterFactory(rxJavaFactory)
                            .addConverterFactory(scalarsFactory)
                            .addConverterFactory(gsonFactory)
                            .build();
                    DebugUtils.log("create blog retrofit");
                }
            }
        }
        return blogRetrofit;
    }

    public static Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(rxJavaFactory)
                .build();
    }

}
