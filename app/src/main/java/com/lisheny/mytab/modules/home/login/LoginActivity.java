package com.lisheny.mytab.modules.home.login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.lisheny.mytab.R;
import com.lisheny.mytab.javabeens.UserBeen;
import com.lisheny.mytab.modules.home.HomeActivity;
import com.lisheny.mytab.modules.home.change.ChangeActivity;
import com.lisheny.mytab.modules.home.register.RegisterActivity;
import com.lisheny.mytab.mvp.BaseApplication;
import com.lisheny.mytab.mvp.MVPBaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    @InjectView(R.id.et_login_usename)
    EditText etLoginUsename;
    @InjectView(R.id.et_login_password)
    EditText etLoginPassword;
    @InjectView(R.id.btn_login)
    ImageButton btnLogin;
    @InjectView(R.id.tv_login_register)
    TextView tvLoginRegister;
    @InjectView(R.id.tv_login_forget)
    TextView tvLoginForget;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void loginSuccess(UserBeen userBeen) {
        ToastUtils.showShortToast(userBeen.getErrmsg());
        progressDialog.dismiss();

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailed(String message) {
        progressDialog.dismiss();
        ToastUtils.showShortToast(R.string.login_Failure);
    }

    @OnClick({R.id.btn_login,R.id.tv_login_register, R.id.tv_login_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (etLoginUsename.getText().toString().trim().equals("admin") &&
                        etLoginPassword.getText().toString().trim().equals("123")){
                    UserBeen userBeen = new UserBeen();
                    userBeen.setId(001);
                    userBeen.setLanguage("1");
                    userBeen.setNickname("测试账号");
                    userBeen.setUsername("admin");
                    userBeen.setVer("1.0");
                    BaseApplication.setUseBeen(userBeen);

                    ToastUtils.showLongToast("未曾登陆服务器，测试账号进入程序。。。");
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                }

                if (!progressDialog.isShowing()) {
                    mPresenter.login(etLoginUsename.getText().toString().trim(),
                            etLoginPassword.getText().toString().trim());
                    progressDialog.setTitle(R.string.user_login);
                    progressDialog.setMessage(getString(R.string.login_ing));
                    progressDialog.show();
                }
                break;
            case R.id.tv_login_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.tv_login_forget:
                Intent gotoChangeAty = new Intent(LoginActivity.this, ChangeActivity.class);
                gotoChangeAty.putExtra("changeType",4);
                startActivity(gotoChangeAty);
                break;
        }
    }
}
