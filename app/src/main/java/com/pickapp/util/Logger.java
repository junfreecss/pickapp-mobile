package com.pickapp.util;

import android.util.Log;

import com.google.gson.Gson;
import com.pickapp.app.AppConstant;

import java.util.Objects;

public class Logger {

    public static void V(Object object) {
        Log.v(AppConstant.APP_NAME, new Gson().toJson(object));
    }

    public static void V(String log) {
        Log.v(AppConstant.APP_NAME, log);
    }

}
