package com.lisheny.mytab.modules.home.face;

import com.lisheny.mytab.javabeens.FaceListBeen;
import com.lisheny.mytab.mvp.BasePresenter;
import com.lisheny.mytab.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FaceContract {
    interface View extends BaseView {
        void getFaceSuccess(FaceListBeen faceListBeen);

        void getFaceFailed(String msg);
    }

    interface  Presenter extends BasePresenter<View> {
        void getAllFaces();
    }
}
