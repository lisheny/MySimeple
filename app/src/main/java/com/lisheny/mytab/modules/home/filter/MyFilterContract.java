package com.lisheny.mytab.modules.home.filter;

import com.lisheny.mytab.javabeens.ResponseBeen;
import com.lisheny.mytab.mvp.BasePresenter;
import com.lisheny.mytab.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyFilterContract {
    interface View extends BaseView {
        void getMyFilterSuccess(Object o);

        void getMyFilterFailed(String msg);

        void addOrDelSuccess(ResponseBeen responseBeen, int position);

        void addOrDelFailed(String str);
    }

    interface Presenter extends BasePresenter<View> {
        void getMyFilters();

        void addOrDelFilter(String filterno, int optype, int position);
    }
}
