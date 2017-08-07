package com.lisheny.mytab.modules.home.register;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.blankj.utilcode.utils.ToastUtils;
import com.lisheny.mytab.R;
import com.lisheny.mytab.javabeens.ResponseBeen;
import com.lisheny.mytab.modules.home.login.LoginActivity;
import com.lisheny.mytab.mvp.MVPBaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RegisterActivity extends MVPBaseActivity<RegisterContract.View, RegisterPresenter> implements RegisterContract.View {

    @InjectView(R.id.et_register_email)
    EditText etRegisterEmail;
    @InjectView(R.id.linearlayout_register_email)
    LinearLayout linearlayoutRegisterEmail;
    @InjectView(R.id.et_register_usename)
    EditText etRegisterUsename;
    @InjectView(R.id.et_register_code)
    EditText etRegisterCode;
    @InjectView(R.id.btn_register_getcode)
    Button btnRegisterGetcode;
    @InjectView(R.id.linearlayout_register_code)
    LinearLayout linearlayoutRegisterCode;
    @InjectView(R.id.et_register_alias)
    EditText etRegisterAlias;
    @InjectView(R.id.et_register_password)
    EditText etRegisterPassword;
    @InjectView(R.id.btn_register)
    ImageButton btnRegister;
    @InjectView(R.id.linearlayout_register_phone)
    LinearLayout linearlayoutRegisterPhone;

    private int registerType = 1; //注册类型： 1手机号码注册 ，2邮箱注册

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);

        initData();
        initView();
    }

    @OnClick({R.id.btn_register_getcode, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register_getcode:
                mPresenter.getCode(etRegisterUsename.getText().toString().trim());

                //使按钮不可点击
                btnRegisterGetcode.setEnabled(false);
                break;
            case R.id.btn_register:
                if (registerType == 1) {
                    mPresenter.register(etRegisterUsename.getText().toString().trim(),
                            etRegisterPassword.getText().toString().trim(),
                            etRegisterCode.getText().toString().trim(),
                            etRegisterAlias.getText().toString().trim(),
                            registerType);
                } else {
                    mPresenter.emailRegister(etRegisterEmail.getText().toString().trim(),
                            etRegisterPassword.getText().toString().trim(),
                            etRegisterAlias.getText().toString().trim());
                }

                //使按钮不可点击
                btnRegister.setEnabled(false);
                break;
        }
    }

    private void initData() {
        Intent intent = getIntent();
        registerType = intent.getIntExtra("registerType", 1);
    }

    private void initView() {
        if (registerType == 1) {
            linearlayoutRegisterPhone.setVisibility(View.VISIBLE);
            linearlayoutRegisterEmail.setVisibility(View.GONE);
            linearlayoutRegisterCode.setVisibility(View.VISIBLE);
        } else {
            linearlayoutRegisterEmail.setVisibility(View.VISIBLE);
            linearlayoutRegisterCode.setVisibility(View.GONE);
            linearlayoutRegisterPhone.setVisibility(View.GONE);
        }
    }

    @Override
    public void getCodeSuccess(ResponseBeen responseBeen) {
        btnRegisterGetcode.setEnabled(true);

        if (responseBeen.getErrcode().equals("1")) {
            ToastUtils.showShortToast(responseBeen.getErrmsg());
        }
    }

    @Override
    public void getCodeFailure(String msg) {
        btnRegisterGetcode.setEnabled(true);
    }

    @Override
    public void registerSuccess(ResponseBeen responseBeen) {
        btnRegister.setEnabled(true);

        if (responseBeen.getErrcode().equals("1")) {
            ToastUtils.showShortToast(responseBeen.getErrmsg());
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        }

    }

    @Override
    public void registerFailure(String msg) {
        btnRegister.setEnabled(true);
    }
}
