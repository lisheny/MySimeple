package com.lisheny.mytab;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lisheny.mytab.adapter.TestAdapter;
import com.lisheny.mytab.javabeens.ExpandListBeen;
import com.lisheny.mytab.javabeens.Student;
import com.lisheny.mytab.widget.BottomNavigationViewEx;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @InjectView(R.id.btn_simple_use)
    Button btnSimpleUse;
    @InjectView(R.id.btn_action_use)
    Button btnActionUse;
    @InjectView(R.id.btn_map_use)
    Button btnMapUse;
    @InjectView(R.id.recy_list)
    RecyclerView recyList;

    private List<ExpandListBeen.AppsBean> mDatas;
    private TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initData();
        initView();
    }

    @OnClick({R.id.btn_simple_use, R.id.btn_action_use, R.id.btn_map_use})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_simple_use:

                break;
            case R.id.btn_action_use:

                break;
            case R.id.btn_map_use:

                break;
            default:
                break;
        }
    }

    private void initData() {
        mDatas = new ArrayList<ExpandListBeen.AppsBean>();
        for (int i = 0; i < 32; i++) {
            ExpandListBeen.AppsBean appsBean = new ExpandListBeen.AppsBean();
            if (i %2 == 1){
                appsBean.setAppname("sadfa");
            }
            if (i == 2){
                appsBean.setAppname("she会知道是否");
            }
            if (i == 3){
                appsBean.setAppname("sh道是否");
            }
            if (i == 4){
                appsBean.setAppname("道道道道she会知道是否");
            }
            if (i == 5){
                appsBean.setAppname("道是否");
            }
            mDatas.add(appsBean);
        }
    }

    private void initView() {
        final BottomNavigationViewEx bnve = (BottomNavigationViewEx) findViewById(R.id.bnve);
        bnve.enableAnimation(true);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);

        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = bnve.getMenuItemPosition(item);
                Log.i("当前点击项：", String.valueOf(position));
                Toast.makeText(MainActivity.this, "当前点击项:" + String.valueOf(position), Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        if (mDatas == null) {
            mDatas = new ArrayList<ExpandListBeen.AppsBean>();
        }
        adapter = new TestAdapter(MainActivity.this, mDatas);
        recyList.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));
        recyList.setAdapter(adapter);
        adapter.setOnItemClickLitener(new TestAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }


    /**
     * flatMap 使用
     */
    private void rxjavaFlatMapUse() {
        List<Student> students = new ArrayList<>();
//        Student student1 = new Student();
//        student1.setName("zhang san");
//        students.add(student1);
//
//        Observable.from(students)
//                .flatMap(new Func1<Student, Observable<Course>>() {
//                    @Override
//                    public Observable<Course> call(Student student) {
//                        return Observable.from(student.getCoursesList());
//                    }
//                })
//                .subscribe(new Action1<Course>() {
//                    @Override
//                    public void call(Course course) {
//                        Log.i(TAG, course.getName());
//                    }
//                });

    }

}
