package com.lisheny.mytab.login;

import com.lisheny.mytab.mvp.BasePresenter;
import com.lisheny.mytab.mvp.BaseView;

import java.util.Objects;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginContract {
    interface View extends BaseView {
        void loginSuccess(Objects user);

        void loginFailed(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        void login(String userName,String passWord);
    }
}
