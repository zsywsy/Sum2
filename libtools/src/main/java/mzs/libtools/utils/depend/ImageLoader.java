//package mzs.libtools.utils.depend;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Handler;
//import android.os.Message;
//import android.widget.ImageView;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import mzs.libtools.utils.BitmapUtils;
//import mzs.libtools.utils.depend.DiskLruCacheUtils;
//import mzs.libtools.utils.HttpUtils;
//import mzs.libtools.utils.LruCacheUtils;
//import mzs.libtools.utils.init.DisplayUtils;
//
///**
// * Created by 24275 on 2016/7/6.
// */
//public class ImageLoader {
//
//    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);
//
//    public static void setImage(final ImageView iv, final String url, int resId, boolean loadable) {
//        Bitmap bitmap;
//        if ((bitmap = LruCacheUtils.getInstance().getBitmapFromMemCache(url)) != null) {
//            iv.setImageBitmap(bitmap);
//            return;
//        }
//        iv.setImageResource(resId);
//        if (!loadable) {
//            return;
//        }
////        DebugUtils.log("size:" + LruCacheUtils.getInstance().getCacheSize());
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//
//                String filePath;
//                Bitmap bitmap;
//                if (!DiskLruCacheUtils.getInstance().checkExistByUrl(url)) {
//                    DiskLruCacheUtils.getInstance().addCache(url);
//                }
//                filePath = DiskLruCacheUtils.getInstance().getFilePathByUrl(url);
//                if (filePath == null || !(new File(filePath).isFile())) {
//                    return;
//                }
//                bitmap = BitmapUtils.decodeBitmapFromFile(filePath, DisplayUtils.getInstance().dp2px(80), DisplayUtils.getInstance().dp2px(80));
////                DebugUtils.log(filePath);
////                DebugUtils.log("count:"+bitmap.getByteCount()+";"+bitmap.getWidth()+";"+bitmap.getHeight());
//                LruCacheUtils.getInstance().addBitmapToMemoryCache(url, bitmap);
//
//                if (url.equals(iv.getTag()))
//                    handler.obtainMessage(1, new IvBm(iv, bitmap)).sendToTarget();
//            }
//        });
//    }
//
//    private static Bitmap getBitmap(String url) throws IOException {
//        InputStream is = HttpUtils.getDownloadConn(url).getInputStream();
//        return BitmapFactory.decodeStream(is);
//    }
//
//    private static Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    IvBm ivBm = (IvBm) msg.obj;
//                    ivBm.iv.setImageBitmap(ivBm.bm);
//                    break;
//            }
//        }
//    };
//
//    static class IvBm {
//        ImageView iv;
//        Bitmap bm;
//
//        public IvBm(ImageView iv, Bitmap bm) {
//            this.iv = iv;
//            this.bm = bm;
//        }
//    }
//
//}
