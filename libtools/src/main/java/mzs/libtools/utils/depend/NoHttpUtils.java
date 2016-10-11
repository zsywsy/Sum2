//package mzs.libtools.utils.depend;
//
//import com.yolanda.nohttp.NoHttp;
//import com.yolanda.nohttp.download.DownloadQueue;
//import com.yolanda.nohttp.rest.OnResponseListener;
//import com.yolanda.nohttp.rest.Request;
//import com.yolanda.nohttp.rest.RequestQueue;
//import com.yolanda.nohttp.rest.Response;
//
///**
// * Created by 24275 on 2016/5/25.
// */
//
///**
// *
// */
//public class NoHttpUtils {
//
//    private static RequestQueue requestQueue;
//    private static DownloadQueue downloadQueue;
//
//    public static RequestQueue getRequestQueue() {
//        if (requestQueue == null) {
//            synchronized (NoHttpUtils.class) {
//                if ((requestQueue == null)) {
//                    requestQueue = NoHttp.newRequestQueue();
//                }
//            }
//        }
//        return requestQueue;
//    }
//
//    public static DownloadQueue getDownloadQueue() {
//        if (downloadQueue == null) {
//            synchronized (NoHttpUtils.class) {
//                if ((downloadQueue == null)) {
//                    downloadQueue = NoHttp.newDownloadQueue();
//                }
//            }
//        }
//        return downloadQueue;
//    }
//
//    public static void test(){
//        Request request;
//        NoHttpUtils.getRequestQueue().add(1, null, new OnResponseListener<Object>() {
//            @Override
//            public void onFailed(int i, String s, Object o, Exception e, int i1, long l) {
//
//            }
//
//            @Override
//            public void onFinish(int i) {
//
//            }
//
//            @Override
//            public void onStart(int i) {
//
//            }
//
//            @Override
//            public void onSucceed(int i, Response<Object> response) {
//
//            }
//        });
//    }
//
//
//}
