package com.lisheny.mytab.tools;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : lisheny
 *     e-mail : 1020044519@qq.com
 *     time   : 2017/04/07
 *     desc   : activity统一调度
 *     version: 1.0
 * </pre>
 */
public class ActivityCollection {
    private static List<Activity> sList = new ArrayList<Activity>();

    public static void add(Activity activity){
        sList.add(activity);
    }

    public static void remove(Activity activity){
        sList.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity:sList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
