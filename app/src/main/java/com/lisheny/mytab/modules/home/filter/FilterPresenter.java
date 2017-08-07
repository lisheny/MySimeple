package com.lisheny.mytab.modules.home.filter;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

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
 *  邮箱 784787081@qq.com
 */

public class FilterPresenter extends BasePresenterImpl<FilterContract.View> implements FilterContract.Presenter{

    private static final String TAG = FilterPresenter.class.getSimpleName();

    @Override
    public void getAllFilter() {
        getfilters();
    }

    @Override
    public void addOrDelFilter(String filterno, int optype) {
        addOrDel(filterno,optype);
    }

    @Override
    public void playSound(SoundPool soundPool,Context context) {
        play(soundPool,context);
    }

    private void getfilters(){
        DoNetWork doNetWork = new DoNetWork(RequestPackage.GET_FILTER_COMMAND);
        doNetWork.getFilters(new MyOkHttpResponseHandler(){
            @Override
            public void onSucceed(String response, int id) {
                super.onSucceed(response, id);
                Gson gson = new Gson();
                Type type = new TypeToken<FilterListBeen>(){}.getType();
                FilterListBeen filterListBeen = gson.fromJson(response,type);

                mView.getFilterSuccess(filterListBeen);
            }

            @Override
            public void onFailure(Call call, Exception e, int id) {
                super.onFailure(call, e, id);

                mView.getFilterFailed(e.toString());
            }
        });

    }

    private void addOrDel(String filterno, int optype){
        DoNetWork doNetWork = new DoNetWork(RequestPackage.ADD_DEL_FILTER);
        doNetWork.addOrDelFilter(filterno,optype,new MyOkHttpResponseHandler(){
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

    /**
     * 播放声音
     * @param soundPool
     * @param context
     */
    private void play(final SoundPool soundPool, Context context) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        float audioMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volumnCurrent = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        final float volumnRatio = volumnCurrent / audioMaxVolumn;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                soundPool.play(1, volumnRatio, volumnRatio, 10, 0, 1f);
            }
        }).start();
    }
}
