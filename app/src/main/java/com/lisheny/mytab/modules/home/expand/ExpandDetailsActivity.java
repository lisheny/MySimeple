package com.lisheny.mytab.modules.home.expand;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lisheny.mytab.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 拓展应用详情
 */

public class ExpandDetailsActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.iv_expand_app_logo)
    ImageView ivExpandAppLogo;
    @InjectView(R.id.tv_expand_app_name)
    TextView tvExpandAppName;
    @InjectView(R.id.tv_expand_version)
    TextView tvExpandVersion;
    @InjectView(R.id.tv_expand_content)
    TextView tvExpandContent;
    @InjectView(R.id.btn_expand_install)
    Button btnExpandInstall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_details);
        ButterKnife.inject(this);

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

    @OnClick(R.id.btn_expand_install)
    public void onViewClicked() {
        Intent intent = new Intent(ExpandDetailsActivity.this,ExpandInstallActivity.class);
        startActivity(intent);
        finish();
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
    }
}
