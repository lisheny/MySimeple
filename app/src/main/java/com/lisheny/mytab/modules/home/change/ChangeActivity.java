package com.lisheny.mytab.modules.home.change;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
 * 修改用户名 修改密码
 */

public class ChangeActivity extends MVPBaseActivity<ChangeContract.View, ChangePresenter> implements ChangeContract.View {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_change_title)
    TextView tvChangeTitle;
    @InjectView(R.id.et_change_content)
    EditText etChangeContent;
    @InjectView(R.id.tv_change_get_code)
    TextView tvChangeGetCode;
    @InjectView(R.id.et_change_input_code)
    EditText etChangeInputCode;
    @InjectView(R.id.btn_change_submit)
    Button btnChangeSubmit;
    @InjectView(R.id.et_change_pwd)
    EditText etChangePwd;

    private int changeType = 1; //1为修改用户名  2修改手机号 3修改密码 4忘记密码——重置密码

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        ButterKnife.inject(this);

        initData();
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.tv_change_get_code, R.id.btn_change_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_change_get_code:
                //获取验证码——重置密码
                mPresenter.forgetCode(etChangeContent.getText().toString().trim());
                break;
            case R.id.btn_change_submit:
                switch (changeType){
                    case 1:
                        mPresenter.changeUsername(etChangeContent.getText().toString().trim());
                        break;
                    case 2:
                        mPresenter.changeMobile(etChangeContent.getText().toString().trim());
                        break;
                    case 3:
                        mPresenter.changePwd(etChangeContent.getText().toString().trim(),
                                etChangePwd.getText().toString().trim());
                        break;
                    case 4:
                        mPresenter.forgetReset(etChangeContent.getText().toString().trim(),
                                etChangeInputCode.getText().toString().trim(),
                                etChangePwd.getText().toString().trim());
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    private void initView() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }

        if (changeType == 1) {
            tvChangeTitle.setText(R.string.change_usename);
            etChangeContent.setHint(R.string.usename);
            tvChangeGetCode.setVisibility(View.GONE);
            etChangeInputCode.setVisibility(View.GONE);
            etChangePwd.setVisibility(View.GONE);
        } else if (changeType == 2){
            tvChangeTitle.setText(R.string.change_phone);
            etChangeContent.setHint(R.string.input_new_phone);
            tvChangeGetCode.setVisibility(View.GONE);
            etChangeInputCode.setVisibility(View.GONE);
            etChangePwd.setVisibility(View.GONE);
        }else if (changeType == 3){
            tvChangeTitle.setText(R.string.change_pwd);
            etChangeContent.setHint(R.string.input_old_pwd);
            tvChangeGetCode.setVisibility(View.GONE);
            etChangeInputCode.setVisibility(View.GONE);
            etChangePwd.setVisibility(View.VISIBLE);
        }else {
            tvChangeTitle.setText(R.string.reset_pwd);
            etChangeContent.setHint(R.string.get_code);
            etChangePwd.setHint(R.string.reset_pwd);
            tvChangeGetCode.setVisibility(View.VISIBLE);
            etChangeInputCode.setVisibility(View.VISIBLE);
            etChangePwd.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        Intent intent = getIntent();
        changeType = intent.getIntExtra("changeType", 1);
    }

    @Override
    public void changeSuccess(ResponseBeen responseBeen) {
        if (responseBeen.getErrcode().equals("1")){
            ToastUtils.showShortToast(responseBeen.getErrmsg());
            finish();
        }
    }

    @Override
    public void changeFailed(String str) {

    }

    @Override
    public void resetSuccess(ResponseBeen responseBeen) {
        ToastUtils.showShortToast(responseBeen.getErrmsg());

        startActivity(new Intent(ChangeActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void resetFailed(String str) {

    }

    @Override
    public void getCodeSuccess(ResponseBeen responseBeen) {
        ToastUtils.showShortToast(responseBeen.getErrmsg());
    }

    @Override
    public void getCodeFailed(String str) {

    }
}
