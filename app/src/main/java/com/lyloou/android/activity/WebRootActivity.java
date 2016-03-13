package com.lyloou.android.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lyloou.android.R;
import com.lyloou.android.util.NetUtil;
import com.lyloou.android.util.ResUtil;
import com.lyloou.android.util.ToastUtil;

public class WebRootActivity extends BaseActivity implements OnClickListener {
    private static final String DATA_URL = "DATA_URL";

    private WebView mWvContent;
    private String mUrl;
    private TextView mTvTips;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_activity_webroot);
        mUrl = getIntent().getStringExtra(DATA_URL);
        initView();

        initStatus(true);
    }

    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, WebRootActivity.class);
        intent.putExtra(DATA_URL, url);
        context.startActivity(intent);
    }

    private void initStatus(boolean isFirst) {
        if (NetUtil.netIsAvailable(this)) {
            if (isFirst) {
                mProgressBar.setVisibility(View.VISIBLE);
                mWvContent.setVisibility(View.GONE);
                mTvTips.setVisibility(View.GONE);
            }
        } else {
            mTvTips.setText(R.string.try_again_by_click_screen);
            mTvTips.setVisibility(View.VISIBLE);
            mWvContent.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {

        mTvTips = (TextView) findViewById(R.id.tv_tips);
        mTvTips.setOnClickListener(this);

        mProgressBar = (ProgressBar) findViewById(R.id.pb_progress_bar);

        mWvContent = (WebView) findViewById(R.id.wv_content);
        mWvContent.getSettings().setJavaScriptEnabled(true);
        // set zoom availabale?
        // wvNew.getSettings().setBuiltInZoomControls(true);

        mWvContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                view.loadUrl(url);
                ;
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                showErrorResult();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                showRightResult();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                initStatus(false);
            }

        });
        mWvContent.loadUrl(mUrl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWvContent.canGoBack()) {
            mWvContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tips:
                clickForRetry();
                break;
            default:
                break;
        }

    }

    private void showRightResult() {
        mProgressBar.setVisibility(View.GONE);
        mWvContent.setVisibility(View.VISIBLE);
    }

    private void showErrorResult() {
        if (NetUtil.netIsAvailable(this)) {
            mTvTips.setText(ResUtil.getStr(this, R.string.can_not_open_current_page));
            mTvTips.setVisibility(View.VISIBLE);
            mWvContent.setVisibility(View.GONE);
        }
    }

    private void clickForRetry() {
        if (NetUtil.netIsAvailable(this)) {
            mWvContent.reload();
            mTvTips.setVisibility(View.GONE);
            mWvContent.setVisibility(View.VISIBLE);
        } else {
            mTvTips.setVisibility(View.VISIBLE);
            mWvContent.setVisibility(View.GONE);
            ToastUtil.show(this, R.string.network_is_not_connected);
        }
    }
}
