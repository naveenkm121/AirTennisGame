package com.dhisat.naveen.airtennisgame.presenter;

import android.content.Context;
import android.util.Log;

import com.dhisat.naveen.airtennisgame.constants.AppConstants;

/**
 * Created by naveen on 11/6/16.
 */
public class DebugHandler {
    private static final String LOG_TAG = "tennislog";

    public static void LogException(Exception e) {
        if (AppConstants.EnableLogging) {
            Log.e(LOG_TAG, "Exception- ", e);
        }

    }

    public static void Log(String message) {
        if (AppConstants.EnableLogging && message != null) {
            Log.i(LOG_TAG, message);
        }
    }

    public static void ReportException(Context context, Throwable e) {
        if (null == context) {
            return;
        }
        if (AppConstants.EnableLogging) {
            Log.e(LOG_TAG, "Exception- ", e);
        }
    }
}