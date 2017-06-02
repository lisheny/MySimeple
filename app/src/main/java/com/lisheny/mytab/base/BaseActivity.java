package com.lisheny.mytab.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lisheny.mytab.tools.ActivityCollection;

/**
 * <pre>
 *     author : lisheny
 *     e-mail : 1020044519@qq.com
 *     time   : 2017/04/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BaseActivity extends AppCompatActivity { protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("BaseActivity",getClass().getSimpleName());
        mContext = getApplicationContext();
        ActivityCollection.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollection.remove(this);
    }

    protected void finishAll() {
        ActivityCollection.finishAll();
    }
}
