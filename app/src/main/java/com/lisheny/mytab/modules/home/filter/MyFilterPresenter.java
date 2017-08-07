package com.lisheny.mytab.modules.home.filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisheny.mytab.http.DoNetWork;
import com.lisheny.mytab.http.MyOkHttpResponseHandler;
import com.lisheny.mytab.http.RequestPackage;
import com.lisheny.mytab.javabeens.FilterListBeen;
import com.lisheny.mytab.mvp.BasePresenterImpl;

import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyFilterPresenter extends BasePresenterImpl<MyFilterContract.View> implements MyFilterContract.Presenter {

    @Override
    public void getMyFilters() {
        getfilters();
    }

    @Override
    public void addOrDelFilter(String filterno, int optype, int position) {
        addOrDel(filterno, optype, position);
    }

    private void getfilters() {
        DoNetWork doNetWork = new DoNetWork(RequestPackage.GET_MYFILTERS_COMMAND);
        doNetWork.getFilters(new MyOkHttpResponseHandler() {
            @Override
            public void onSucceed(String response, int id) {
                super.onSucceed(response, id);
                Gson gson = new Gson();
                Type type = new TypeToken<FilterListBeen>() {
                }.getType();
                FilterListBeen filterListBeen = gson.fromJson(response, type);

                mView.getMyFilterSuccess(filterListBeen);
            }

            @Override
            public void onFailure(Call call, Exception e, int id) {
                super.onFailure(call, e, id);

                mView.getMyFilterFailed(e.toString());
            }
        });
    }

    private void addOrDel(String filterno, int optype, int position) {
        DoNetWork doNetWork = new DoNetWork(RequestPackage.ADD_DEL_FILTER);
        doNetWork.addOrDelFilter(filterno, optype, new MyOkHttpResponseHandler() {
            @Override
            public void onSucceed(String response, int id) {
                super.onSucceed(response, id);
            }

            @Override
            public void onFailure(Call call, Exception e, int id) {
                super.onFailure(call, e, id);
            }
        });
    }
}
