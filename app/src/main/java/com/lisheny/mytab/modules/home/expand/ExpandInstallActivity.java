package com.lisheny.mytab.modules.home.expand;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lisheny.mytab.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ExpandInstallActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_expand_install_title)
    TextView tvExpandInstallTitle;
    @InjectView(R.id.tv_expand_version)
    TextView tvExpandVersion;
    @InjectView(R.id.pgb_expand_install)
    ProgressBar pgbExpandInstall;
    @InjectView(R.id.tv_expand_install_state)
    TextView tvExpandInstallState;
    @InjectView(R.id.btn_expand_finish)
    Button btnExpandFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_install);
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

    @OnClick(R.id.btn_expand_finish)
    public void onViewClicked() {
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
