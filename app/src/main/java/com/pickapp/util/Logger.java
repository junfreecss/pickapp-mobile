package com.pickapp.util;

import android.util.Log;

import com.google.gson.Gson;
import com.pickapp.app.AppConstant;

public class Logger {

    public static void V(String name, Object object) {
        Log.v(AppConstant.APP_NAME, name+": "+ new Gson().toJson(object));
    }

    public static void V(String name, String log) {
        Log.v(AppConstant.APP_NAME, name+": "+log);
    }

    public static void E(String name, Object object) {
        Log.e(AppConstant.APP_NAME, name +": "+new Gson().toJson(object));
    }

    public static void E(String name, String log) {
        Log.e(AppConstant.APP_NAME, name +": "+ log);
    }

}
