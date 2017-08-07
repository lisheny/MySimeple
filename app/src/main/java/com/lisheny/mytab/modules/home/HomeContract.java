package com.lisheny.mytab.modules.home;

import android.app.Activity;

import com.lisheny.mytab.javabeens.ResponseBeen;
import com.lisheny.mytab.mvp.BasePresenter;
import com.lisheny.mytab.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HomeContract {
    interface View extends BaseView {
        void xzingSuccess(ResponseBeen responseBeen);

        void xzingFailed(String error);
    }

    interface  Presenter extends BasePresenter<View> {
        void appUpdate(Activity activity);

        void resetBleHeadset(String str);
    }
}
