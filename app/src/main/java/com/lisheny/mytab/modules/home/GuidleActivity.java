package com.lisheny.mytab.modules.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.lisheny.mytab.R;
import com.lisheny.mytab.modules.home.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GuidleActivity extends AppCompatActivity {

    @InjectView(R.id.iv_guide_pic)
    ImageView ivGuidePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidle);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(
                GuidleActivity.this, R.anim.guide_show);
        ivGuidePic.startAnimation(alphaAnimation);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(GuidleActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }).start();
    }
}
