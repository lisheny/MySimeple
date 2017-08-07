package com.lisheny.mytab.modules.home.face;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisheny.mytab.http.DoNetWork;
import com.lisheny.mytab.http.MyOkHttpResponseHandler;
import com.lisheny.mytab.http.RequestPackage;
import com.lisheny.mytab.javabeens.FaceListBeen;
import com.lisheny.mytab.mvp.BasePresenterImpl;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FacePresenter extends BasePresenterImpl<FaceContract.View> implements FaceContract.Presenter{

    private static final String TAG = FacePresenter.class.getSimpleName();

    @Override
    public void getAllFaces() {
        getFaces();
    }

    private void getFaces(){
        DoNetWork doNetWork  = new DoNetWork(RequestPackage.GET_FACE_COMMAND);
        doNetWork.getFaces(new MyOkHttpResponseHandler(){
            @Override
            public void onSucceed(String response, int id) {
                super.onSucceed(response, id);
                Gson gson = new Gson();
                Type type  = new TypeToken<FaceListBeen>(){}.getType();
                FaceListBeen faceListBeen = gson.fromJson(response,type);

                mView.getFaceSuccess(faceListBeen);
            }

            @Override
            public void onFailure(Call call, Exception e, int id) {
                super.onFailure(call, e, id);

                mView.getFaceFailed(e.toString());
            }
        });
    }
}
