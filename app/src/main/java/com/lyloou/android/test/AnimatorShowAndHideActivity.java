package com.lyloou.android.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lyloou.android.R;

public class AnimatorShowAndHideActivity extends AppCompatActivity {
    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_show_and_hide);

        initView();
    }

    private void initView() {
        Button btnShow = (Button) findViewById(R.id.btn_show);
        Button btnHide = (Button) findViewById(R.id.btn_hide);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        btnShow.setOnClickListener(mListener);
        btnHide.setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_show:
                    if (mTvContent.getVisibility() == View.GONE) {
                        animView(mTvContent, 0, 200, true);
                    }
                    break;
                case R.id.btn_hide:
                    if (mTvContent.getVisibility() == View.VISIBLE) {

                        animView(mTvContent, mTvContent.getLayoutParams().height, 0, false);
                    }
            }
        }
    };

    private void animView(final View v, int start, int end, final boolean isToShow) {

        ValueAnimator va = ValueAnimator.ofInt(start, end);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int h = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = h;
                v.setLayoutParams(layoutParams);
                v.requestLayout();
            }
        });

        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (isToShow) {
                    v.setVisibility(View.VISIBLE);
                }
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!isToShow) {
                    v.setVisibility(View.GONE);
                }
            }
        });
        va.setDuration(200);
        va.start();
    }
}
