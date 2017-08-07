package com.lisheny.mytab.modules.home.login;

import com.lisheny.mytab.javabeens.UserBeen;
import com.lisheny.mytab.mvp.BasePresenter;
import com.lisheny.mytab.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseView {
        void loginSuccess(UserBeen userBeen);

        void loginFailed(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        void login(String userName,String passWord);
    }
}
