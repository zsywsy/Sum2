package mzs.libapp.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class BaseApp extends Application {

    private PackageInfo packageInfo;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    /**
     * 获取App安装包信息
     *
     * @return app packageInfo
     */
    public PackageInfo getPackageInfo() {
        if (packageInfo == null) {
            synchronized (BaseApp.this) {
                if (packageInfo == null) {
                    try {
                        packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                    } catch (NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (packageInfo == null) {
            packageInfo = new PackageInfo();
        }

        return packageInfo;
    }
}
