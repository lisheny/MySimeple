package com.lisheny.mytab.tools;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.lisheny.mytab.mvp.BaseApplication;


/**
 * author：kang
 * time:  2016-11-21
 */
public class VersionUtils {

    /**
     * 获取versionCode
     */
    public static int getVersionCode() {
        Application application = BaseApplication.getAppContext();
        PackageManager pm = application.getPackageManager();
        PackageInfo pinfo = null;
        try {
            pinfo = pm.getPackageInfo(application.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (pinfo != null) {
            return pinfo.versionCode;
        }else {
            return 1;
        }
    }

    /**
     * 获取VersionName
     */
    public static String getVersionName() {
        BaseApplication instance = BaseApplication.getAppContext();
        PackageManager pm = instance.getPackageManager();
        PackageInfo pinfo = null;
        try {
            pinfo = pm.getPackageInfo(instance.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (pinfo != null) {
            return pinfo.versionName;
        }else {
            return "1.0";
        }
    }
}
