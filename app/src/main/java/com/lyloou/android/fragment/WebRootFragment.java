package com.lyloou.android.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lyloou.android.R;
import com.lyloou.android.util.NetUtil;
import com.lyloou.android.util.ResUtil;
import com.lyloou.android.util.ToastUtil;

public class WebRootFragment extends BaseFragment implements OnClickListener {
	private static final String EXTRA_DATA_URL = "extra_data_url";

	private WebView mWvContent;
	private Context mContext;
	private String mUrl;
	private TextView mTvTips;
	private ProgressBar mProgressBar;
	
	public WebRootFragment(){
		super();
	}
	
	public static WebRootFragment newInstance(String url){
		WebRootFragment fragment = new WebRootFragment();
		Bundle bundle = new Bundle();
		bundle.putString(EXTRA_DATA_URL, url);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_fragment_webroot, container, false);
		initView(view);
		initStatus();
		return view;
	}
	
	private void initData() {
		mContext = getActivity();
		mUrl = getArguments().getString(EXTRA_DATA_URL);
	}

	
	@SuppressLint("SetJavaScriptEnabled")
	private void initView(View view){
		
		mTvTips = (TextView) view.findViewById(R.id.tv_tips);
		mTvTips.setOnClickListener(this);
		
		mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
		
		mWvContent = (WebView) view.findViewById(R.id.wv_more_help);
		mWvContent.getSettings().setJavaScriptEnabled(true);
		// zoom is available?
		// mWvContent.getSettings().setBuiltInZoomControls(true);
		
		mWvContent.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if(url.startsWith("tel:")){
					Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
					startActivity(intent);
					return true;
				}
				view.loadUrl(url);;
				return true;
			}
			
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);
				showErrorResult();
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				showRightResult();
			}
			
		});
		
		mWvContent.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(keyCode == KeyEvent.KEYCODE_BACK && mWvContent.canGoBack()){
					mWvContent.goBack();
					return true;
				}
				return false;
			}
		});
		
		mWvContent.loadUrl(mUrl);
	}
	
	private void initStatus(){
		if(NetUtil.netIsAvailable(mContext)){
			mTvTips.setVisibility(View.GONE);
			mProgressBar.setVisibility(View.VISIBLE);
		} else {
			mTvTips.setVisibility(View.VISIBLE);
			mWvContent.setVisibility(View.GONE);
			mProgressBar.setVisibility(View.GONE);
		}
	}
	
	private void showRightResult(){
		mProgressBar.setVisibility(View.GONE);
		mWvContent.setVisibility(View.VISIBLE);
	}
	
	private void showErrorResult(){
		if(NetUtil.netIsAvailable(mContext)){
			mTvTips.setText(ResUtil.getStr(mContext, R.string.can_not_open_current_page));
			mTvTips.setVisibility(View.VISIBLE);
			mWvContent.setVisibility(View.GONE);
		}
	}
	
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.tv_tips:
			clickForRetry();
			break;
		default:
			break;
		}
	}

	private void clickForRetry() {
		if(NetUtil.netIsAvailable(mContext)){
			mWvContent.reload();
			mTvTips.setVisibility(View.GONE);
			mProgressBar.setVisibility(View.VISIBLE);
		} else {
			mTvTips.setVisibility(View.VISIBLE);
			mWvContent.setVisibility(View.GONE);
			ToastUtil.show(mContext, R.string.network_is_not_connected);
		}
	}
	
}
