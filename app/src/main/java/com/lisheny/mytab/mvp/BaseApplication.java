package com.lisheny.mytab.mvp;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;
import com.lisheny.mytab.javabeens.UserBeen;

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

    private static UserBeen userBeen;

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

        //工具库初始化
        Utils.init(appContext);
    }

    public static UserBeen getUseBeen() {
        return userBeen;
    }

    public static void setUseBeen(UserBeen userBeen) {
        BaseApplication.userBeen = userBeen;
    }
}
