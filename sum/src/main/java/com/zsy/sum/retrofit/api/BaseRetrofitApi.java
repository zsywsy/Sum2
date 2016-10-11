package com.zsy.sum.retrofit.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by 24275 on 2016/9/19.
 */
public interface BaseRetrofitApi {

    @GET
    Observable<String> getContent(@Url String servlet);

}
