package mzs.libapp.app;

import android.content.pm.PackageInfo;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import mzs.libtools.utils.DateTimeUtils;
import mzs.libtools.utils.DirUtils;
import mzs.libtools.utils.IOUtils;


/**
 * Created by 24275 on 2016/5/25.
 */
public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler defaultHandler;
    private static BaseApp baseApp;


    public MyExceptionHandler(BaseApp baseApp) {
        MyExceptionHandler.baseApp = baseApp;
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        new Thread() {
            @Override
            public void run() {
                PrintWriter writer = null;
                File logFile = new File(getErrLogDir(), getErrLogName());
                try {
                    writer = new PrintWriter(new FileWriter(logFile, true));
                    writer.write(getBaseInfo());
                    ex.printStackTrace(writer);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.close(writer);
                }
            }
        }.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        defaultHandler.uncaughtException(thread, ex);
    }


    public static File getErrLogDir() {
        File docDir = DirUtils.getExPrivateDir(baseApp, Environment.DIRECTORY_DOCUMENTS);
        File errLogDir = new File(docDir, "errLog");
        if (!errLogDir.isDirectory()) {
            errLogDir.mkdirs();
        }
        return errLogDir;
    }

    private String getErrLogName() {
        return DateTimeUtils.getNowStr(DateTimeUtils.DEFAULT_NAME_FORMAT) + ".txt";
    }

    private String getBaseInfo() {
        PackageInfo packageInfo = baseApp.getPackageInfo();
        StringBuffer sb = new StringBuffer();
        String datetime = DateTimeUtils.getNowStr(DateTimeUtils.DEFAULT_DATETIME_FORMAT);
        sb.append("Datetime: " + datetime + "\n");
        sb.append("Version: " + packageInfo.versionName + "(" + packageInfo.versionCode + ")\n");
        sb.append("Android: " + android.os.Build.VERSION.RELEASE + "(" + android.os.Build.MODEL + ")\n");
        return sb.toString();
    }
}
