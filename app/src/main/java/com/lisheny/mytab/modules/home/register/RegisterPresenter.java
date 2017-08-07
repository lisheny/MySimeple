package com.lisheny.mytab.modules.home.register;

import android.text.TextUtils;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisheny.mytab.R;
import com.lisheny.mytab.http.DoNetWork;
import com.lisheny.mytab.http.MyOkHttpResponseHandler;
import com.lisheny.mytab.http.RequestPackage;
import com.lisheny.mytab.javabeens.ResponseBeen;
import com.lisheny.mytab.mvp.BasePresenterImpl;

import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RegisterPresenter extends BasePresenterImpl<RegisterContract.View> implements RegisterContract.Presenter {

    @Override
    public void getCode(String phoneNumber) {
        getcode(phoneNumber);
    }

    @Override
    public void register(String username, String pwd, String code, String nickname, int registerType) {
        registerService(username, pwd, code, nickname, registerType);
    }

    @Override
    public void emailRegister(String userName, String pwd, String nickname) {
        emailReggisterService(userName,pwd,nickname);
    }

    private void getcode(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            ToastUtils.showShortToast(R.string.input_username_null);
            return;
        }
        DoNetWork doNetWork = new DoNetWork(RequestPackage.GETCODE_COMMAND);
        doNetWork.getCode(phoneNumber, new MyOkHttpResponseHandler() {
            @Override
            public void onSucceed(String response, int id) {
                super.onSucceed(response, id);
                Gson gson = new Gson();
                Type type = new TypeToken<ResponseBeen>(){}.getType();
                ResponseBeen responseBeen = gson.fromJson(response,type);

                mView.getCodeSuccess(responseBeen);
            }

            @Override
            public void onFailure(Call call, Exception e, int id) {
                super.onFailure(call, e, id);
                mView.getCodeFailure(e.toString());
            }
        });
    }

    private void registerService(String userName, String passWord, String code, String nickname, int registerType) {
        if (TextUtils.isEmpty(userName)) {
            ToastUtils.showShortToast(R.string.input_username_null);
            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            ToastUtils.showShortToast(R.string.input_pwd_null);
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShortToast(R.string.input_code_null);
            return;
        }
        if (TextUtils.isEmpty(nickname)) {
            ToastUtils.showShortToast(R.string.input_ailas_null);
            return;
        }
        DoNetWork doNetWork = new DoNetWork(RequestPackage.REGISTER_COMMAND);
        doNetWork.register(userName, passWord, code, nickname, registerType, new MyOkHttpResponseHandler() {
            @Override
            public void onSucceed(String response, int id) {
                super.onSucceed(response, id);
                Gson gson = new Gson();
                Type type = new TypeToken<ResponseBeen>(){}.getType();
                ResponseBeen responseBeen = gson.fromJson(response,type);
                mView.registerSuccess(responseBeen);
            }

            @Override
            public void onFailure(Call call, Exception e, int id) {
                super.onFailure(call, e, id);
                mView.registerFailure(e.toString());
            }
        });

    }

    private void emailReggisterService(String userName, String passWord, String nickname){
        if (TextUtils.isEmpty(userName)) {
            ToastUtils.showShortToast(R.string.input_username_null);
            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            ToastUtils.showShortToast(R.string.input_pwd_null);
            return;
        }
        if (TextUtils.isEmpty(nickname)) {
            ToastUtils.showShortToast(R.string.input_ailas_null);
            return;
        }
        DoNetWork doNetWork = new DoNetWork(RequestPackage.EMAIL_REGISTER_COMMAND);
        doNetWork.emailRegister(userName, passWord, nickname, new MyOkHttpResponseHandler() {
            @Override
            public void onSucceed(String response, int id) {
                super.onSucceed(response, id);
                Gson gson = new Gson();
                Type type = new TypeToken<ResponseBeen>(){}.getType();
                ResponseBeen responseBeen = gson.fromJson(response,type);
                mView.registerSuccess(responseBeen);
            }

            @Override
            public void onFailure(Call call, Exception e, int id) {
                super.onFailure(call, e, id);
                mView.registerFailure(e.toString());
            }
        });
    }
}
