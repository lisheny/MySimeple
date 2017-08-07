package com.lisheny.mytab.modules.home.expand;

import com.lisheny.mytab.javabeens.ExpandListBeen;
import com.lisheny.mytab.mvp.BasePresenter;
import com.lisheny.mytab.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ExpandContract {
    interface View extends BaseView {
        void getExpandSuccess(ExpandListBeen expandListBeen);

        void getExpandFailed(String msg);
    }

    interface  Presenter extends BasePresenter<View> {
        void getExpandList();
    }
}
