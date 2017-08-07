package com.lisheny.mytab.modules.home;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisheny.mytab.R;
import com.lisheny.mytab.http.DoNetWork;
import com.lisheny.mytab.http.MyOkHttpResponseHandler;
import com.lisheny.mytab.http.RequestPackage;
import com.lisheny.mytab.javabeens.ResponseBeen;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class WebViewActivity extends AppCompatActivity {

    @InjectView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.inject(this);

        //获取网页显示URL
        getUri();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)&&webview.canGoBack()){
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getUri(){
        DoNetWork doNetWork = new DoNetWork(RequestPackage.GET_URL);
        doNetWork.getUrl(new MyOkHttpResponseHandler(){
            @Override
            public void onSucceed(String response, int id) {
                super.onSucceed(response, id);

                Logger.d(response);
                Gson gson = new Gson();
                Type type = new TypeToken<ResponseBeen>(){}.getType();
                ResponseBeen responseBeen = gson.fromJson(response,type);

                if (null != responseBeen){
                    initWebView(responseBeen.getUrl());
                }
            }

            @Override
            public void onFailure(Call call, Exception e, int id) {
                super.onFailure(call, e, id);
            }
        });
    }

    private void initWebView(String url) {
        webview.loadUrl(url);
        webview.requestFocusFromTouch();
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//LOAD_CACHE_ELSE_NETWORK

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.d("网页加载进度：", String.valueOf(newProgress));
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Logger.d("onPageFinished");
                dismissProgressDialog();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Logger.d("onPageStarted");
                showProgressDialog();
            }
        });

    }

    private ProgressDialog progressDialog;
    private void showProgressDialog(){
        if (progressDialog == null){
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setTitle("");
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();
    }

    private void dismissProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }
}
