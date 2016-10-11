package com.zsy.sum.utils;

import mzs.libtools.utils.depend.DebugUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaUtils {

    private static Subscriber<Object> debugSubscriber = new DebugSubscriber<>();

    public static Subscriber<Object> getDebugSubscriber() {
        return debugSubscriber;
    }

    public static class DebugSubscriber<T> extends Subscriber<T> {

        @Override
        public void onStart() {
            DebugUtils.log("onStart");
        }

        @Override
        public void onCompleted() {
            DebugUtils.log("onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            DebugUtils.log("onError:" + (e == null ? null : e.getMessage()));
        }

        @Override
        public void onNext(T t) {
            DebugUtils.log("onNext:" + t);
        }
    }


    /**
     * useless,just a try
     *
     * @param <T>
     */
    public static class IOMainTransformer<T> implements Observable.Transformer<T, T> {
        @Override
        public Observable<T> call(Observable<T> observable) {
            return observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }


}
