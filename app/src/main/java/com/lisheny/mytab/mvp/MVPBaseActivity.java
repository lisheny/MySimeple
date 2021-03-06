package com.lisheny.mytab.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lisheny.mytab.tools.ActivityCollection;

import java.lang.reflect.ParameterizedType;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public abstract class MVPBaseActivity<V extends BaseView,T extends BasePresenterImpl<V>> extends AppCompatActivity implements BaseView{
    public T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter= getInstance(this,1);
        mPresenter.attachView((V) this);

        Log.d("BaseActivity",getClass().getSimpleName());
        ActivityCollection.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
        mPresenter.detachView();

        ActivityCollection.remove(this);
    }

    @Override
    public Context getContext(){
        return this;
    }

    public  <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }


    protected void finishAll() {
        ActivityCollection.finishAll();
    }
}
