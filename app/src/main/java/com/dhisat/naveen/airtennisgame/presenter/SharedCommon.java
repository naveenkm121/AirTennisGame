package com.dhisat.naveen.airtennisgame.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.dhisat.naveen.airtennisgame.constants.AppConstants;

/**
 * Created by naveen on 17/6/16.
 */
public class SharedCommon {

    public static String getSpeakerState(Context context)
    {
        SharedPreferences prefs= context.getSharedPreferences(AppConstants.UserSharedPref, 0);
        String userName=prefs.getString(AppConstants.SpeakerState, "On");
        return userName;
    }
    public static void setSpeakerState(Context context,String condition)
    {

        SharedPreferences prefs= context.getSharedPreferences(AppConstants.UserSharedPref, 0);
        SharedPreferences.Editor edit= prefs.edit();
        edit.putString(AppConstants.SpeakerState, condition);
        edit.commit();
    }
}
