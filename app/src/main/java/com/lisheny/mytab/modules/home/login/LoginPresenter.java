package com.lisheny.mytab.modules.home.login;

import android.text.TextUtils;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisheny.mytab.http.DoNetWork;
import com.lisheny.mytab.http.MyOkHttpResponseHandler;
import com.lisheny.mytab.http.RequestPackage;
import com.lisheny.mytab.javabeens.UserBeen;
import com.lisheny.mytab.mvp.BaseApplication;
import com.lisheny.mytab.mvp.BasePresenterImpl;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";


    @Override
    public void login(final String userName, final String passWord) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)){
            ToastUtils.showShortToast("用户名或密码为空！");
            return;
        }
        loginService(userName,passWord);
    }

    private void loginService(String usename, String pwd) {
        DoNetWork doNetWork = new DoNetWork(RequestPackage.LOGIN_COMMAND);
        doNetWork.login(usename, pwd, new MyOkHttpResponseHandler() {
            @Override
            public void onSucceed(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<UserBeen>() {}.getType();
                UserBeen userBeen = gson.fromJson(response,type);

                BaseApplication.setUseBeen(userBeen);
                mView.loginSuccess(userBeen);
                Logger.i(TAG,response);
            }

            @Override
            public void onFailure(Call call, Exception e, int id) {
                mView.loginFailed(e.toString());
            }
        });
    }
}
