package com.lyloou.android.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lyloou.android.R;
import com.lyloou.android.activity.BaseActivity;
import com.lyloou.android.util.AnimatorUtil;
import com.lyloou.android.util.ViewUtil;

public class AnimatorShowAndHideActivity extends BaseActivity {
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
        ViewUtil.clickEffectWithBgByAlpha(mListener, btnShow, btnHide);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_show:
                    if (mTvContent.getVisibility() == View.GONE) {
                        AnimatorUtil.animHeightToView(AnimatorShowAndHideActivity.this, mTvContent, true, 160);
                    }
                    break;
                case R.id.btn_hide:
                    if (mTvContent.getVisibility() == View.VISIBLE) {
                        AnimatorUtil.animHeightToView(AnimatorShowAndHideActivity.this, mTvContent, false, 160);
                    }
            }
        }
    };
}
