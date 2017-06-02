package com.lisheny.mytab.login;

import com.lisheny.mytab.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{

    @Override
    public void login(final String userName, final String passWord) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if ("123".equals(userName) && "123".equals(passWord)){
                    mView.loginSuccess(null);
                }else {
                    mView.loginFailed("登陆失败");
                }
            }
        }).start();

    }
}
