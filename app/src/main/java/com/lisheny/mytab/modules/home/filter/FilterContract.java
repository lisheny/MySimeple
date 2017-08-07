package com.lisheny.mytab.modules.home.filter;

import android.content.Context;
import android.media.SoundPool;

import com.lisheny.mytab.javabeens.FilterListBeen;
import com.lisheny.mytab.javabeens.ResponseBeen;
import com.lisheny.mytab.mvp.BasePresenter;
import com.lisheny.mytab.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FilterContract {
    interface View extends BaseView {
        void getFilterSuccess(FilterListBeen filterListBeen);

        void getFilterFailed(String msg);

        void addOrDelSuccess(ResponseBeen responseBeen);

        void addOrDelFailed(String str);
    }

    interface  Presenter extends BasePresenter<View> {
        void getAllFilter();

        void addOrDelFilter(String filterno,int optype);

        void playSound(SoundPool soundPool, Context context);
    }
}
