package com.lyloou.android.util;

/**
 * Created by admin on 2016/3/17.
 */

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.lyloou.android.R;
import com.lyloou.android.app.LouApp;

public class DialogUtil {
    public static final int MODE_QUIT_ALL = 666000;

    public void show(Context context, int mode, Intent intent) {
        switch (mode) {
            case MODE_QUIT_ALL:
                quitAll(context, intent);
                break;
        }
    }

    public void show(Context context, int mode) {
        show(context, mode, null);
    }


    // ------ 退出程序的对话框

    /**
     * @param context 上下文
     * @param intent  用于context 和 Dialog 交互数据；可以设置为null(表示不传给Dialog值);
     */
    private static void quitAll(Context context, Intent intent) {
        final Dialog dialog = new Dialog(context, R.style.exit_dialog);
        dialog.setContentView(R.layout.dialog_quit_all);

        Button btnOk = (Button) dialog.findViewById(R.id.btn_quit_all_dialog_ok);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_quit_all_dialog_cancel);

        OnClickListener dialogListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_quit_all_dialog_ok:
                        dialog.dismiss();
                        LouApp.finishAll();
                        break;
                    case R.id.btn_quit_all_dialog_cancel:
                        dialog.dismiss();
                        break;
                }
            }
        };
        ViewUtil.clickEffectWithBgByAlpha(dialogListener, btnOk, btnCancel);

        // 设置弹框位置及透明度
        Window window = dialog.getWindow();
        window.setGravity(Gravity.TOP | Gravity.END);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = ScreenUtil.dp2Px(context, 16);
        lp.y = ScreenUtil.dp2Px(context, 50);
        lp.alpha = 0.96f;

        window.setAttributes(lp);
        dialog.setCancelable(true);
        dialog.show();
    }// ~~~
}
