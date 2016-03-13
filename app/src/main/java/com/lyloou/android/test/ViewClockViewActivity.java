package com.lyloou.android.test;

import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.lyloou.android.R;
import com.lyloou.android.activity.BaseActivity;
import com.lyloou.android.fragment.BaseFragment;
import com.lyloou.android.util.ViewUtil;
import com.lyloou.android.util.ViewUtil.Click;
import com.lyloou.android.view.ClockView;

public class ViewClockViewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //  initClockView();
        initClockSurfaceView();
    }

    private void initClockSurfaceView() {
        setContentView(R.layout.activity_view_clock_surfaceview);
    }

    private void initClockView() {
        setContentView(R.layout.activity_view_clock_view);
        ClockView clockViewTop = (ClockView) findViewById(R.id.clock_view_top);
        ClockView clockViewBottom = (ClockView) findViewById(R.id.clock_view_bottom);
        ViewUtil.click(clockViewTop, clickWithData(R.id.clock_view_top));
        ViewUtil.click(clockViewBottom, clickWithData(R.id.clock_view_bottom));
    }

    private Click clickWithData(final int rId) {
        return new Click() {
            @Override
            public void click() {
                switch (rId) {
                    case R.id.clock_view_top:
                        Toast.makeText(ViewClockViewActivity.this, "你点我做啥，赶快干活去！", Toast.LENGTH_LONG).show();
                        ;
                        break;
                    case R.id.clock_view_bottom:
                        Toast.makeText(ViewClockViewActivity.this, "活干完了，好好休息去吧！", Toast.LENGTH_LONG).show();
                        ;
                        break;
                }
            }
        };
    }
}
