package com.lisheny.mytab.modules.home;

import android.app.Activity;
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

public class HomePresenter extends BasePresenterImpl<HomeContract.View> implements HomeContract.Presenter{

    @Override
    public void appUpdate(Activity activity) {
        update(activity);
    }

    @Override
    public void resetBleHeadset(String str) {
        bandleBle(str);
    }

    private void update(Activity activity){
        AppUpdateBiz appUpdateBiz = AppUpdateBiz.getAppUpdateBiz(activity);
        appUpdateBiz.setCommand(RequestPackage.UPDATE_COMMAND);
        appUpdateBiz.haveNewVersion(null, true, true, new MyOnAppUpdateListener());
    }

    private void bandleBle(String devid){
        if (TextUtils.isEmpty(devid)){
            ToastUtils.showShortToast(R.string.ble_id_null);
            return;
        }

        DoNetWork doNetWork = new DoNetWork(RequestPackage.BANDLE_BLE_COMMAND);
        doNetWork.bandleBleHeadset(devid,new MyOkHttpResponseHandler(){
            @Override
            public void onFailure(Call call, Exception e, int id) {
                    super.onFailure(call, e, id);
                mView.xzingFailed(e.toString());
            }

            @Override
            public void onSucceed(String response, int id) {
                super.onSucceed(response, id);
                Gson gson = new Gson();
                Type type = new TypeToken<ResponseBeen>() {}.getType();
                ResponseBeen responseBeen = gson.fromJson(response,type);

                mView.xzingSuccess(responseBeen);
            }
        });
    }

}
