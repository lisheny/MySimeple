package com.lisheny.mytab.modules.home.filter;


import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.lisheny.mytab.R;
import com.lisheny.mytab.adapter.FilterListAdapter;
import com.lisheny.mytab.javabeens.FilterListBeen;
import com.lisheny.mytab.javabeens.ResponseBeen;
import com.lisheny.mytab.mvp.BaseApplication;
import com.lisheny.mytab.mvp.MVPBaseActivity;
import com.lisheny.mytab.widget.TagGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 * 滤镜商店 modulse
 */

public class FilterActivity extends MVPBaseActivity<FilterContract.View, FilterPresenter>
        implements FilterContract.View {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_filter_title)
    TextView tvFilterTitle;
    @InjectView(R.id.tv_filter_state)
    TextView tvFilterState;
    @InjectView(R.id.tv_filter_all)
    TextView tvFilterAll;
    @InjectView(R.id.tv_filter_recently)
    TextView tvFilterRecently;
    @InjectView(R.id.tag_group)
    TagGroup tagGroup;
    @InjectView(R.id.btn_goto_myfilter)
    Button btnGotoMyfilter;
    @InjectView(R.id.recy_filter_list)
    RecyclerView recyFilterList;

    private List<FilterListBeen.FiltersBean> mDatas;
    private FilterListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_shop);
        ButterKnife.inject(this);
        initView();
        initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.tv_filter_all, R.id.tv_filter_recently, R.id.btn_goto_myfilter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_filter_all:
                tvFilterAll.setTextColor(getResources().getColor(R.color.colorAccent));
                tvFilterRecently.setTextColor(getResources().getColor(R.color.write));
                break;
            case R.id.tv_filter_recently:
                tvFilterAll.setTextColor(getResources().getColor(R.color.write));
                tvFilterRecently.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.btn_goto_myfilter:
                startActivity(new Intent(FilterActivity.this, MyFilterActivity.class));
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

        if (mDatas == null) {
            mDatas = new ArrayList<FilterListBeen.FiltersBean>();
        }
        adapter = new FilterListAdapter(FilterActivity.this, mDatas);
        recyFilterList.setItemAnimator(new DefaultItemAnimator());
        recyFilterList.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));
        recyFilterList.setAdapter(adapter);
        adapter.setOnItemClickLitener(new FilterListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                // TODO: 2017/7/27 test: 播放一段声音
                SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_SYSTEM, 5);
                soundPool.load(FilterActivity.this, R.raw.error, 1);
                mPresenter.playSound(soundPool, FilterActivity.this);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //添加到我的滤镜
                mPresenter.addOrDelFilter(mDatas.get(position).getFilterno(),1);
            }
        });

        tvFilterAll.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        tvFilterRecently.setTextColor(ContextCompat.getColor(this, R.color.write));
        tagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                ToastUtils.showShortToast("点击了:" + tag);

                SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_SYSTEM, 5);
                soundPool.load(FilterActivity.this, R.raw.error, 1);
                mPresenter.playSound(soundPool, FilterActivity.this);
            }
        });
    }

    private void initData() {
        if (mDatas == null) {
            mDatas = new ArrayList<FilterListBeen.FiltersBean>();
        }

        //test
        if (null != BaseApplication.getUseBeen() && "admin".equals(BaseApplication.getUseBeen().getUsername())) {
            tagGroup.setVisibility(View.VISIBLE);
            recyFilterList.setVisibility(View.GONE);
            tagGroup.setTags(new String[]{"改革001", "0奔小康0004", "开放002", "0改革开放03", "0改革开放03", "开放002", "0改革开放03", "开放002", "0改革开放03", "开放002", "0改革开放03", "0奔小康0004", "0改革开放03", "0奔小康0004"});
        } else {
            //获取滤镜
            mPresenter.getAllFilter();
        }
    }

    @Override
    public void getFilterSuccess(FilterListBeen filterListBeen) {
        mDatas.clear();
        mDatas.addAll(filterListBeen.getFilters());
        adapter.notifyDataSetChanged();

        List<String> filtersName = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            filtersName.add(mDatas.get(i).getFiltername());
        }
        tagGroup.setTags(filtersName);

        if (mDatas.size() == 0) {
            ToastUtils.showShortToast(R.string.data_is_empty);
        }
    }

    @Override
    public void getFilterFailed(String msg) {

    }

    @Override
    public void addOrDelSuccess(ResponseBeen responseBeen) {

    }

    @Override
    public void addOrDelFailed(String str) {

    }
}
