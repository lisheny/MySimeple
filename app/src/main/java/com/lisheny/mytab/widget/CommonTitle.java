package com.lisheny.mytab.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lisheny.mytab.R;

/**
 * <pre>
 *     author : lisheny
 *     e-mail : 1020044519@qq.com
 *     time   : 2017/04/07
 *     desc   : 复用标题
 *     version: 1.0
 * </pre>
 */
public class CommonTitle extends LinearLayout {

    private TextView commonTitle;
    private ImageView btnBack;

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title) {
        commonTitle.setText(title);
    }

    /**
     * 隐藏返回图标
     * @param isGone
     */
    public void setGone(boolean isGone){
        if (isGone){
            btnBack.setVisibility(GONE);
        }else {
            btnBack.setVisibility(VISIBLE);
        }
    }

    public CommonTitle(Context context) {
        super(context);
    }

    public CommonTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.common_title, this);
        btnBack = (ImageView)findViewById(R.id.btn_back);
        commonTitle = (TextView)findViewById(R.id.common_title);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });
    }
}

