package com.lyloou.android.test;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.lyloou.android.R;
import com.lyloou.android.util.Ulog;

public class AnimLoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_loading);

        initView1();
        initView2();
        initView3();
    }

    private void initView1() {
        final TextView t = (TextView) findViewById(R.id.tv_anim_loading_1);
        final String str = "....................";
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, str.length() + 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int count = (int) animation.getAnimatedValue();
                Spannable spannable = new SpannableString(str);
                if(count>str.length()){
                    count = str.length();
                }
                spannable.setSpan(new ForegroundColorSpan((int) (Color.CYAN * Math.random())), count - 1, count, 0);
                t.setText(spannable);
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
    }

    private void initView2() {
        final TextView tv = (TextView) findViewById(R.id.tv_anim_loading_2);
        final String str = "Loading...";
        ValueAnimator valueAnimator = ValueAnimator.ofInt(7, str.length());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int count = (int) animation.getAnimatedValue();
                Spannable spannable = new SpannableString(str);
                spannable.setSpan(new ForegroundColorSpan(Color.CYAN), count, count + 1, 0);
                tv.setText(spannable);
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
    }

    private void initView3() {
        final TextView tv = (TextView) findViewById(R.id.tv_anim_loading_3);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 3);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                String str = "Loading";
                int count = (int) animation.getAnimatedValue();
                for (int i = 0; i <= count; i++) {
                    str = str + ".";
                }
                tv.setText(str);
            }
        });
        valueAnimator.setDuration(3000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
    }
}
