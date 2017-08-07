package com.lisheny.mytab.modules.home.face;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.lisheny.mytab.adapter.FaceListAdapter;
import com.lisheny.mytab.javabeens.FaceListBeen;
import com.lisheny.mytab.modules.home.filter.MyFilterActivity;
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
 * 表情商店
 */

public class FaceActivity extends MVPBaseActivity<FaceContract.View, FacePresenter> implements FaceContract.View {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_face_title)
    TextView tvFaceTitle;
    @InjectView(R.id.tv_face_state)
    TextView tvFaceState;
    @InjectView(R.id.tv_face_all)
    TextView tvFaceAll;
    @InjectView(R.id.tv_face_recently)
    TextView tvFaceRecently;
    @InjectView(R.id.tag_group)
    TagGroup tagGroup;
    @InjectView(R.id.btn_filter)
    Button btnFilter;
    @InjectView(R.id.recy_face_list)
    RecyclerView recyFaceList;

    private List<FaceListBeen.ExpsBean> mDatas;
    private FaceListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_shop);
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

    @OnClick({R.id.tv_face_all, R.id.tv_face_recently, R.id.btn_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_face_all:
                tvFaceAll.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvFaceRecently.setTextColor(getResources().getColor(R.color.write));
                break;
            case R.id.tv_face_recently:
                tvFaceAll.setTextColor(getResources().getColor(R.color.write));
                tvFaceRecently.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.btn_filter:
                startActivity(new Intent(FaceActivity.this, MyFilterActivity.class));
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
            mDatas = new ArrayList<FaceListBeen.ExpsBean>();
        }
        adapter = new FaceListAdapter(FaceActivity.this, mDatas);
        recyFaceList.setItemAnimator(new DefaultItemAnimator());
        recyFaceList.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));
        recyFaceList.setAdapter(adapter);
        adapter.setOnItemClickLitener(new FaceListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        tvFaceAll.setTextColor(getResources().getColor(R.color.colorPrimary));
        tvFaceRecently.setTextColor(getResources().getColor(R.color.write));
        tagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                ToastUtils.showShortToast("点击了:" + tag);
            }
        });
    }

    private void initData() {
        if (null == mDatas) {
            mDatas = new ArrayList<FaceListBeen.ExpsBean>();
        }

        //test UI测试账号显示
        if ("admin".equals(BaseApplication.getUseBeen().getUsername())) {
            tagGroup.setVisibility(View.VISIBLE);
            recyFaceList.setVisibility(View.GONE);
            tagGroup.setTags(new String[]{"改革001", "0奔小康0004", "开放002", "0改革开放03", "0改革开放03", "开放002", "0改革开放03", "开放002", "0改革开放03", "开放002", "0改革开放03", "0奔小康0004", "0改革开放03", "0奔小康0004"});
        } else {
            //获取表情
            mPresenter.getAllFaces();
        }
    }

    @Override
    public void getFaceSuccess(FaceListBeen faceListBeen) {
        mDatas.clear();
        mDatas.addAll(faceListBeen.getExps());
        adapter.notifyDataSetChanged();

        List<String> ExpsName = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            ExpsName.add(mDatas.get(i).getExpname());
        }
        tagGroup.setTags(ExpsName);

        if (mDatas.size() == 0) {
            ToastUtils.showShortToast(R.string.data_is_empty);
        }
    }

    @Override
    public void getFaceFailed(String msg) {

    }
}
