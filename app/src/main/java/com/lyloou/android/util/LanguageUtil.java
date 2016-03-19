package com.lyloou.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.util.DisplayMetrics;


import com.lyloou.android.app.LouApp;
import com.lyloou.android.constants.Constant;
import com.lyloou.android.lou.activity.ListActivity;

import java.util.Locale;

public class LanguageUtil {
    // 语言；
    public static final String LAN_EN = "EN";
    public static final String LAN_FR = "FR";
    public static final String LAN_DE = "DE";
    public static final String LAN_NL = "NL";


    public static void switchTo(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        if (!config.locale.equals(locale)) {
            config.locale = locale;
            DisplayMetrics dm = resources.getDisplayMetrics();
            resources.updateConfiguration(config, dm);
        }
    }

    public static void switchTo(Context context, String language) {
        Locale locale = new Locale(language);
        switch (language) {
            case LAN_EN:
                locale = Locale.ENGLISH;
                break;
            case LAN_DE:
                locale = Locale.GERMANY;
                break;
            case LAN_FR:
                locale = Locale.FRANCE;
                break;
            case LAN_NL:
                locale = new Locale("nl", "NL");
                break;
        }
        switchTo(context, locale);
    }

    // ~~~ 以下属于本应用特有
    public static void switchTo(final Context context, String language, boolean needRestart, SharedPreferencesUtil spu) {
        String currentLanguage = spu.getLanguage();

        // 如果要设置的语言和当前语言一样，则什么都不做
        if (currentLanguage.equals(language)) {
            return;
        }
        spu.saveLanguage(language);
        if (needRestart) {
            if (context instanceof Activity) {
                ToastUtil.show(context, "Please wait...");
            }

            // 关闭所有界面，并重新打开 MainActivity
            LouApp.finishAll();
            // 需要延时打开，否则会导致 服务中的 conn leak;
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent intent = new Intent(context, ListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                }
            }, 3000);
        } else {
            switchTo(context, language);
        }
    }


}
