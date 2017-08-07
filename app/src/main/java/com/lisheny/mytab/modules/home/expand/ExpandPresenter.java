package com.lisheny.mytab.modules.home.expand;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisheny.mytab.http.DoNetWork;
import com.lisheny.mytab.http.MyOkHttpResponseHandler;
import com.lisheny.mytab.http.RequestPackage;
import com.lisheny.mytab.javabeens.ExpandListBeen;
import com.lisheny.mytab.mvp.BasePresenterImpl;

import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ExpandPresenter extends BasePresenterImpl<ExpandContract.View> implements ExpandContract.Presenter{

    @Override
    public void getExpandList() {
        getExpands();
    }

    private void getExpands(){
        DoNetWork doNetWork = new DoNetWork(RequestPackage.GET_EXPAND_LIST_COMMAND);
        doNetWork.getExpandList(new MyOkHttpResponseHandler(){
            @Override
            public void onSucceed(String response, int id) {
                super.onSucceed(response, id);
                Gson gson = new Gson();
                Type type = new TypeToken<ExpandListBeen>(){}.getType();
                ExpandListBeen expandListBeen = gson.fromJson(response,type);

                mView.getExpandSuccess(expandListBeen);
            }

            @Override
            public void onFailure(Call call, Exception e, int id) {
                super.onFailure(call, e, id);
                mView.getExpandFailed(e.toString());
            }
        });
    }
}
