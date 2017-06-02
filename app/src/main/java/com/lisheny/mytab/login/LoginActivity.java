package com.lisheny.mytab.login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lisheny.mytab.MainActivity;
import com.lisheny.mytab.R;
import com.lisheny.mytab.mvp.MVPBaseActivity;

import java.util.Objects;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.login("123", "123");
        progressDialog.setTitle("登陆");
        progressDialog.setMessage("登陆中。。。");
        progressDialog.show();
    }

    @Override
    public void loginSuccess(Objects user) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.setMessage("登陆成功。。。");
                progressDialog.dismiss();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void loginFailed(String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.setMessage("登陆失败。。。");
                progressDialog.dismiss();
            }
        });
    }
}
