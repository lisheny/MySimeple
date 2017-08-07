package com.lisheny.mytab.modules.home;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lisheny.mytab.MainActivity;
import com.lisheny.mytab.R;
import com.lisheny.mytab.adapter.HomeListAdapter;
import com.lisheny.mytab.javabeens.ResponseBeen;
import com.lisheny.mytab.javabeens.Student;
import com.lisheny.mytab.modules.home.change.ChangeActivity;
import com.lisheny.mytab.modules.home.expand.ExpandActivity;
import com.lisheny.mytab.modules.home.expand.ExpandInstallActivity;
import com.lisheny.mytab.modules.home.face.FaceActivity;
import com.lisheny.mytab.modules.home.filter.FilterActivity;
import com.lisheny.mytab.mvp.MVPBaseActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.drakeet.materialdialog.MaterialDialog;

public class HomeActivity extends MVPBaseActivity<HomeContract.View, HomePresenter> implements
        HomeContract.View, NavigationView.OnNavigationItemSelectedListener {

    @InjectView(R.id.toolbar_title)
    TextView toolbarTitle;
    @InjectView(R.id.toolbar_right)
    TextView toolbarRight;
    @InjectView(R.id.iv_home_right)
    ImageView ivHomeRight;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.nav_view)
    NavigationView navView;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.recy_home_list)
    RecyclerView recyHomeList;
    @InjectView(R.id.btn_home_filter)
    Button btnHomeFilter;
    @InjectView(R.id.btn_home_face)
    Button btnHomeFace;

    private List<Student> mDatas;
    private HomeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);

        initData();
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                //绑定蓝牙耳机
                mPresenter.resetBleHeadset(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_change_name) {
            Intent gotoChangeAty = new Intent(HomeActivity.this, ChangeActivity.class);
            gotoChangeAty.putExtra("changeType",1);
            startActivity(gotoChangeAty);

        } else if (id == R.id.nav_change_number) {
            Intent gotoChangeAty = new Intent(HomeActivity.this, ChangeActivity.class);
            gotoChangeAty.putExtra("changeType",2);
            startActivity(gotoChangeAty);
        }else if (id == R.id.nav_change_pwd){
            Intent gotoChangeAty = new Intent(HomeActivity.this, ChangeActivity.class);
            gotoChangeAty.putExtra("changeType",3);
            startActivity(gotoChangeAty);
        } else if (id == R.id.nav_update_app) {
            mPresenter.appUpdate(HomeActivity.this);

        } else if (id == R.id.nav_update_headset) {
            Intent gotoInstallAty = new Intent(HomeActivity.this, ExpandInstallActivity.class);
            startActivity(gotoInstallAty);
        } else if (id == R.id.nav_reset_headset) {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setOrientationLocked(true);
            integrator.initiateScan();
        } else if (id == R.id.nav_clean) {
            dialogShow();
        }else if (id == R.id.nav_feedback) {
            ToastUtils.showShortToast("test UI");
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
        }else if (id == R.id.nav_agreement) {
            startActivity(new Intent(HomeActivity.this, WebViewActivity.class));
        }else {

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //监听 toolbar 返回图标
        if (item.getItemId() == android.R.id.home) {
            ToastUtils.showShortToast("23333");
            Logger.d("233333");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.btn_home_filter, R.id.btn_home_face,R.id.iv_home_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_home_filter:
                Intent gotoFilterAty = new Intent(HomeActivity.this, FilterActivity.class);
                startActivity(gotoFilterAty);
                break;
            case R.id.btn_home_face:
                Intent gotoFaceAty = new Intent(HomeActivity.this, FaceActivity.class);
                startActivity(gotoFaceAty);
                break;
            case R.id.iv_home_right:
                startActivity(new Intent(HomeActivity.this, ExpandActivity.class));
                break;
        }
    }

    private void initData(){
        //test
        mDatas = new ArrayList<Student>();
        for (int i = 0; i < 3; i ++ ){
            Student student = new Student();
            mDatas.add(student);
        }
    }

    private void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbarTitle.setText(R.string.home_title);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationIcon(R.drawable.ic_settings_black_24dp);

        navView.setNavigationItemSelectedListener(this);
        Resources resource = (Resources) getBaseContext().getResources();
        ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.navigation_menu_item_color);
        navView.setBackgroundResource(R.color.app_bg2);
        navView.setItemTextColor(csl);                  //设置字体颜色
        navView.getMenu().getItem(0).setChecked(true);  //设置MenuItem默认选中项

        if (mDatas == null){
            mDatas = new ArrayList<Student>();
        }
        adapter = new HomeListAdapter(HomeActivity.this, mDatas);
        recyHomeList.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        recyHomeList.setAdapter(adapter);
        adapter.setOnItemClickLitener(new HomeListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Logger.d("点击了");
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private void dialogShow(){
        final MaterialDialog materialDialog = new MaterialDialog(HomeActivity.this);
        materialDialog.setTitle(getString(R.string.clean_cache_yes_or_no));
        materialDialog.setMessage(getString(R.string.will_lose_date));
        materialDialog.setPositiveButton(getString(R.string.confirm), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDialog.dismiss();
            }
        }).setNegativeButton(getString(R.string.cancel), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDialog.dismiss();
            }
        });
        materialDialog.show();
    }

    @Override
    public void xzingSuccess(ResponseBeen responseBeen) {
        ToastUtils.showShortToast(responseBeen.getErrmsg());
    }

    @Override
    public void xzingFailed(String error) {

    }
}
