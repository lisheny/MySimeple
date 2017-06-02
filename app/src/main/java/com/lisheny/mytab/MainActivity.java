package com.lisheny.mytab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.lisheny.mytab.base.BaseActivity;
import com.lisheny.mytab.widget.BottomNavigationViewEx;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        final BottomNavigationViewEx bnve = (BottomNavigationViewEx) findViewById(R.id.bnve);
        bnve.enableAnimation(true);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);

        bnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = bnve.getMenuItemPosition(item);
                Log.i("当前点击项：", String.valueOf(position));
                Toast.makeText(MainActivity.this,"当前点击项:"+String.valueOf(position),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
