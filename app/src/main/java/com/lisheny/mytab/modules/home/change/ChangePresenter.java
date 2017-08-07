package com.lisheny.mytab.modules.home.change;

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
 *  邮箱 784787081@qq.com
 */

public class ChangePresenter extends BasePresenterImpl<ChangeContract.View> implements ChangeContract.Presenter{

    @Override
    public void changeUsername(String username) {
        changeUsernameOrMobile(username,1);
    }

    @Override
    public void changeMobile(String phoneNumber) {
        changeUsernameOrMobile(phoneNumber,2);
    }

    @Override
    public void changePwd(String oldPwd, String newPwd) {
        changePassword(oldPwd,newPwd);
    }

    @Override
    public void forgetCode(String str) {
        code(str);
    }

    @Override
    public void forgetReset(String phone,String code, String newpwd) {
        resetPwd(phone,code,newpwd);
    }

    /**
     * changeType: 1--修改用户名 2--修改手机号 3--都修改
     * @param usernameOrPhoneNumber
     * @param changeType
     */
    private void changeUsernameOrMobile(String usernameOrPhoneNumber,int changeType){
        if (TextUtils.isEmpty(usernameOrPhoneNumber)){
            ToastUtils.showShortToast(R.string.change_data_null);
            return;
        }

        DoNetWork doNetWork = new DoNetWork(RequestPackage.CHANGE_COMMAND);
        doNetWork.changeUsenameOrNumber(usernameOrPhoneNumber,usernameOrPhoneNumber,changeType,myOkHttpResponseHandler);
    }

    private void changePassword(String oldPwd, String newPwd){
        if (TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd)){
            ToastUtils.showShortToast(R.string.change_data_null);
            return;
        }

        DoNetWork doNetWork = new DoNetWork(RequestPackage.CHANGE_PWD_COMMAND);
        doNetWork.changePwd(oldPwd,newPwd,myOkHttpResponseHandler);
    }

    /**
     * 获取验证码
     */
    private void code(String phone){
        if (TextUtils.isEmpty(phone)){
            ToastUtils.showShortToast(R.string.input_phone);
            return;
        }
        DoNetWork doNetWork = new DoNetWork(RequestPackage.FORGET_CODE_COMMAND);
        doNetWork.getCode(phone,new MyOkHttpResponseHandler(){
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
                mView.getCodeFailed(e.toString());
            }
        });
    }

    private void resetPwd(String phone,String code,String newpwd){
        DoNetWork doNetWork = new DoNetWork(RequestPackage.FORGET_RESET_COMMAND);
        doNetWork.resetPwd(phone,code,newpwd,new MyOkHttpResponseHandler(){
            @Override
            public void onSucceed(String response, int id) {
                super.onSucceed(response, id);
                Gson gson = new Gson();
                Type type = new TypeToken<ResponseBeen>(){}.getType();
                ResponseBeen responseBeen = gson.fromJson(response,type);

                mView.resetSuccess(responseBeen);
            }

            @Override
            public void onFailure(Call call, Exception e, int id) {
                super.onFailure(call, e, id);

                mView.resetFailed(e.toString());
            }
        });
    }

    private MyOkHttpResponseHandler myOkHttpResponseHandler = new MyOkHttpResponseHandler(){
        @Override
        public void onSucceed(String response, int id) {
            super.onSucceed(response, id);
            Gson gson = new Gson();
            Type type = new TypeToken<ResponseBeen>(){}.getType();
            ResponseBeen responseBeen = gson.fromJson(response,type);

            mView.changeSuccess(responseBeen);
        }

        @Override
        public void onFailure(Call call, Exception e, int id) {
            super.onFailure(call, e, id);

            mView.changeFailed(e.toString());
        }
    };
}
