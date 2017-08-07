package com.lisheny.mytab.modules.home.change;

import com.lisheny.mytab.javabeens.ResponseBeen;
import com.lisheny.mytab.mvp.BasePresenter;
import com.lisheny.mytab.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ChangeContract {
    interface View extends BaseView {
        void changeSuccess(ResponseBeen responseBeen);

        void changeFailed(String str);

        void resetSuccess(ResponseBeen responseBeen);

        void resetFailed(String str);

        void getCodeSuccess(ResponseBeen responseBeen);

        void getCodeFailed(String str);
    }

    interface  Presenter extends BasePresenter<View> {
        void changeUsername(String username);

        void changeMobile(String phoneNumber);

        void changePwd(String oldPwd, String newPwd);

        void forgetCode(String str);

        void forgetReset(String phone,String code,String newpwd);
    }
}
