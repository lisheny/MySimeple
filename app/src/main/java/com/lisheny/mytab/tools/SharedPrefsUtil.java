package com.lisheny.mytab.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <pre>
 *     author : lisheny
 *     e-mail : 1020044519@qq.com
 *     time   : 2017/04/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class SharedPrefsUtil {
    private final static String SETTING = "Setting";
    public static void putValue(Context context, String key, int value) {
        SharedPreferences.Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putInt(key, value);
        sp.apply();
    }
    public static void putValue(Context context, String key, boolean value) {
        SharedPreferences.Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putBoolean(key, value);
        sp.apply();
    }
    public static void putValue(Context context, String key, String value) {
        SharedPreferences.Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putString(key, value);
        sp.apply();
    }
    public static int getValue(Context context, String key, int defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }
    public static boolean getValue(Context context, String key, boolean defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }
    public static String getValue(Context context, String key, String defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }
}
