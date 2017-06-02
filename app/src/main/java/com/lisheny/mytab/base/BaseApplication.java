package com.lisheny.mytab.base;

import android.app.Application;

/**
 * <pre>
 *     author : lisheny
 *     e-mail : 1020044519@qq.com
 *     time   : 2017/04/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BaseApplication extends Application {

    private static BaseApplication appContext;

    public static BaseApplication getAppContext() {
        if (null == appContext) {
            appContext = new BaseApplication();
        }
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
