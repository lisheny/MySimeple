package com.lisheny.mytab.http;


import com.blankj.utilcode.utils.ToastUtils;
import com.lisheny.mytab.R;
import com.orhanobut.logger.Logger;

import okhttp3.Call;

/**
 * author：kang
 * time:  2016-
 */
public class MyOkHttpResponseHandler implements OkHttpResponseHandler {

    private static final String TAG = MyOkHttpResponseHandler.class.getSimpleName() ;

    @Override
    public void onSucceed(String response, int id) {
        String info = TAG + "onSucceed()  -->  " + "  response：" + response + "  id：" + id;
        Logger.d(TAG,info);
    }

    @Override
    public void onFailure(Call call, Exception e, int id) {
        ToastUtils.showShortToast(R.string.network_failure);
        String info = TAG + "onFailure()  -->  call：" + call.request().toString() + "  id：" + id;
        Logger.d(TAG,info);
    }
}
