//package mzs.libtools.utils.depend;
//
//import android.content.Context;
//
//import com.jakewharton.disklrucache.DiskLruCache;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import mzs.libtools.utils.DirUtils;
//import mzs.libtools.utils.EncryptUtils;
//import mzs.libtools.utils.HttpUtils;
//import mzs.libtools.utils.IOUtils;
//import mzs.libtools.utils.init.DisplayUtils;
//
///**
// * Created by 24275 on 2016/6/30.
// */
//public class DiskLruCacheUtils {
//
//    private final String CACHE_DIR = "diskLrcCache";
//    private final int APP_VERSION = 1;
//    private final int VALUE_COUNT = 1;
//    private final int MAX_SIZE = 100 * 1024 * 1024;
//    private final int FILE_INDEX = 0;
//
//    private static DiskLruCacheUtils instance;
//
//    private DiskLruCache diskLruCache;
//
//    private File directory;
//
//    private DiskLruCacheUtils() {
//    }
//
//    public static DiskLruCacheUtils getInstance() {
//        if (instance == null) {
//            synchronized (DisplayUtils.class) {
//                if (instance == null) {
//                    instance = new DiskLruCacheUtils();
//                }
//            }
//        }
//        return instance;
//    }
//
//    public void init(Context context) {
//        diskLruCache = getDefaultDiskLruCache(context);
//    }
//
//    public DiskLruCache getDiskLruCache(File directory, int appVersion, int valueCount, long maxSize) {
//        try {
//            diskLruCache = DiskLruCache.open(directory, appVersion, valueCount, maxSize);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return diskLruCache;
//    }
//
//    private DiskLruCache getDefaultDiskLruCache(Context context) {
//        directory = getDefaultCacheDir(context);
//        if (!directory.isDirectory()) {
//            if (!directory.mkdirs()) {
//                throw new IllegalArgumentException("mkdirs failed");
//            }
//        }
//        return getDiskLruCache(directory, APP_VERSION, VALUE_COUNT, MAX_SIZE);
//    }
//
//    private String url2key(String url) {
//        return EncryptUtils.MD5(url);
//    }
//
//    public void addCache(String url, String key) {
//        if (diskLruCache == null) {
//            throw new NullPointerException("diskLruCache is null");
//        }
//        if (checkExistByKey(key)) {
//            return;
//        }
//        DiskLruCache.Editor editor;
//        InputStream inputStream = null;
//        OutputStream outputStream = null;
//        try {
//            editor = diskLruCache.edit(key);
//            if (editor != null) {
//                outputStream = editor.newOutputStream(FILE_INDEX);
//                inputStream = HttpUtils.getDownloadConn(url).getInputStream();
//                if (IOUtils.is2os(inputStream, outputStream)) {
//                    editor.commit();
//                } else {
//                    editor.abort();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            IOUtils.close(inputStream);
//            IOUtils.close(outputStream);
//        }
//    }
//
//    public void addCache(String url) {
//        addCache(url, url2key(url));
//    }
//
//    private InputStream getCacheByKey(String key) {
//        DiskLruCache.Snapshot snapshot;
//        InputStream is = null;
//        try {
//            snapshot = diskLruCache.get(key);
//            if (snapshot != null) {
//                is = snapshot.getInputStream(FILE_INDEX);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return is;
//    }
//
//    public InputStream getCacheByUrl(String url) {
//        return getCacheByKey(url2key(url));
//    }
//
//    private File getDefaultCacheDir(Context context) {
//        String cacheDir;
//        if (DirUtils.isExWriteable()) {
//            cacheDir = DirUtils.getExCacheDir(context).getPath();
//        } else {
//            cacheDir = DirUtils.getInCacheDir(context).getPath();
//        }
//        return new File(cacheDir + File.separator + CACHE_DIR);
//    }
//
//    public String getFilePathByKey(String key) {
//        String dir = directory.getPath();
//        String filePath = dir + File.separator + key + "." + FILE_INDEX;
//        return filePath;
//    }
//
//    public String getFilePathByUrl(String url) {
//        return getFilePathByKey(url2key(url));
//    }
//
//    private boolean checkExistByKey(String key) {
//        File file = new File(getFilePathByKey(key));
//        return file.isFile();
//    }
//
//    public boolean checkExistByUrl(String url) {
//        return checkExistByKey(url2key(url));
//    }
//
//}
