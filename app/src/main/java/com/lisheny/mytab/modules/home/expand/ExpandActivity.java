package com.lisheny.mytab.modules.home.expand;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.lisheny.mytab.R;
import com.lisheny.mytab.adapter.ExpandListAdapter;
import com.lisheny.mytab.javabeens.ExpandListBeen;
import com.lisheny.mytab.mvp.BaseApplication;
import com.lisheny.mytab.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 * 拓展应用
 */

public class ExpandActivity extends MVPBaseActivity<ExpandContract.View, ExpandPresenter> implements ExpandContract.View {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_expand_state)
    TextView tvExpandState;
    @InjectView(R.id.recy_expand_list)
    RecyclerView recyExpandList;

    private List<ExpandListBeen.AppsBean> mDatas;
    private ExpandListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
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

    private void initView() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }

        if (mDatas == null){
            mDatas = new ArrayList<ExpandListBeen.AppsBean>();
        }
        adapter = new ExpandListAdapter(ExpandActivity.this, mDatas);
        recyExpandList.setItemAnimator(new DefaultItemAnimator());
        recyExpandList.setLayoutManager(new LinearLayoutManager(ExpandActivity.this));
        recyExpandList.setAdapter(adapter);
        adapter.setOnItemClickLitener(new ExpandListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ExpandActivity.this,ExpandDetailsActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private void initData(){
        //test
        if ("admin".equals(BaseApplication.getUseBeen().getUsername())){
            mDatas = new ArrayList<ExpandListBeen.AppsBean>();
            for (int i = 0; i < 5; i ++ ){
                ExpandListBeen.AppsBean appsBean = new ExpandListBeen.AppsBean();
                mDatas.add(appsBean);
            }
         }else {
            //获取拓展应用
            mPresenter.getExpandList();
        }
    }

    @Override
    public void getExpandSuccess(ExpandListBeen expandListBeen) {
        mDatas.clear();
        mDatas.addAll(expandListBeen.getApps());
        adapter.notifyDataSetChanged();

        if (mDatas.size() == 0){
            ToastUtils.showShortToast(R.string.data_is_empty);
        }
    }

    @Override
    public void getExpandFailed(String msg) {

    }
}
