package com.lisheny.mytab.modules.home.register;

import com.lisheny.mytab.javabeens.ResponseBeen;
import com.lisheny.mytab.mvp.BasePresenter;
import com.lisheny.mytab.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RegisterContract {
    interface View extends BaseView {
        void getCodeSuccess(ResponseBeen responseBeen);

        void getCodeFailure(String msg);

        void registerSuccess(ResponseBeen responseBeen);

        void registerFailure(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void getCode(String phoneNumber);

        void register(String userName, String pwd, String code, String nickname,int registerType);

        void emailRegister(String userName, String pwd, String nickname);
    }
}
