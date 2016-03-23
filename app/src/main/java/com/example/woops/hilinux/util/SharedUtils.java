package com.example.woops.hilinux.util;

import android.content.Context;
import android.content.SharedPreferences;

//实现标记的写入与读取
public class SharedUtils {
    public static final String FILE_NAME = "dianping";
    public static void putCityName(Context context,String cityName){
        SharedPreferences.Editor editor = context.getSharedPreferences(FILE_NAME,Context.MODE_APPEND)
                .edit();
        editor.putString("cityName",cityName);
        editor.commit();
    }
    public static String getCityName(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
                .getString("cityName","选择城市");
    }
}
