package io.github.sp4rx.shothanddb;

import android.util.Log;

/**
 * Created by suvajit on 29/11/17.
 */

public class Logger {
    public static boolean isDebuggable = false;

    public static void i(String tag, String msg) {
        if (isDebuggable)
            Log.i(tag, msg);
    }
}
