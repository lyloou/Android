package com.lyloou.android.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.lyloou.android.constants.Constant;

public class SharedPreferencesUtil {
    private static final String SPU_NAME = Constant.PACKAGE_NAME;
    private static final String KEY_LANGUAGE = "KEY_LANGUAGE";
    private static SharedPreferencesUtil sSharedPreferencesUtil;
    private SharedPreferences sp;

    private SharedPreferencesUtil(Context context) {
        sp = context.getSharedPreferences(SPU_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesUtil getInstance(Context context) {
        if (sSharedPreferencesUtil == null) {
            sSharedPreferencesUtil = new SharedPreferencesUtil(context);
        }
        return sSharedPreferencesUtil;
    }

    public void saveLanguage(String language) {
        sp.edit().putString(KEY_LANGUAGE, language).commit();
    }

    public String getLanguage() {
        return sp.getString(KEY_LANGUAGE, "EN");
    }

}
                                                  