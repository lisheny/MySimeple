package com.lisheny.mytab.modules.home.filter;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.lisheny.mytab.R;
import com.lisheny.mytab.adapter.MyFilterListAdapter;
import com.lisheny.mytab.javabeens.FilterListBeen;
import com.lisheny.mytab.javabeens.ResponseBeen;
import com.lisheny.mytab.mvp.BaseApplication;
import com.lisheny.mytab.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MyFilterActivity extends MVPBaseActivity<MyFilterContract.View, MyFilterPresenter> implements MyFilterContract.View {

    @InjectView(R.id.iv_myfilter_right)
    ImageView ivMyfilterRight;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_myfilter_title)
    TextView tvMyfilterTitle;
    @InjectView(R.id.tv_myfilter_state)
    TextView tvMyfilterState;
    @InjectView(R.id.tv_myfilter_all)
    TextView tvMyfilterAll;
    @InjectView(R.id.tv_myfilter_recently)
    TextView tvMyfilterRecently;
    @InjectView(R.id.recy_myfilter_list)
    RecyclerView recyMyfilterList;

    private List<FilterListBeen.FiltersBean> mDatas;
    private MyFilterListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_filter);
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


    @OnClick({R.id.iv_myfilter_right, R.id.tv_myfilter_all, R.id.tv_myfilter_recently})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_myfilter_right:
                ToastUtils.showShortToast("do something");
                break;
            case R.id.tv_myfilter_all:
                break;
            case R.id.tv_myfilter_recently:
                break;
        }
    }

    private void initData() {
        if (null != BaseApplication.getUseBeen() && "admin".equals(BaseApplication.getUseBeen().getUsername())) {
            //test
            mDatas = new ArrayList<FilterListBeen.FiltersBean>();
            for (int i = 0; i < 5; i++) {
                FilterListBeen.FiltersBean filtersBean = new FilterListBeen.FiltersBean();
                mDatas.add(filtersBean);
            }
        } else {
            //获取滤镜
            mPresenter.getMyFilters();
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

        if (mDatas == null) {
            mDatas = new ArrayList<FilterListBeen.FiltersBean>();
        }
        adapter = new MyFilterListAdapter(MyFilterActivity.this, mDatas);
        recyMyfilterList.setItemAnimator(new DefaultItemAnimator());
        recyMyfilterList.setLayoutManager(new LinearLayoutManager(MyFilterActivity.this));
        recyMyfilterList.setAdapter(adapter);
        adapter.setOnItemClickLitener(new MyFilterListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void getMyFilterSuccess(Object o) {
        FilterListBeen filterListBeen = (FilterListBeen) o;
        mDatas.clear();
        mDatas.addAll(filterListBeen.getFilters());
        adapter.notifyDataSetChanged();

        if (mDatas.size() == 0) {
            ToastUtils.showShortToast(R.string.data_is_empty);
        }
    }

    @Override
    public void getMyFilterFailed(String msg) {

    }

    @Override
    public void addOrDelSuccess(ResponseBeen responseBeen, int position) {

    }

    @Override
    public void addOrDelFailed(String str) {

    }
}
