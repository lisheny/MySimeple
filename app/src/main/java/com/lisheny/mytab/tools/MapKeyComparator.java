package com.lisheny.mytab.tools;

import java.util.Comparator;

/**
 * Created by lisheny on 2017/3/3.
 */

class MapKeyComparator implements Comparator<String> {

    @Override
    public int compare(String str1, String str2) {

        return str1.compareTo(str2);
    }
}