package com.trustydroid.customsharingdialog.dialog;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

/**
 * Utility used to perform some actions on intents
 * */
class IntentHelper {

    private IntentHelper(){}

    /**
     * Gets a launch intent for resolved info
     */
    public static Intent getLaunchIntentFrom(PackageManager pm, ResolveInfo info) {
        if (info == null || info.activityInfo == null || info.activityInfo.packageName == null) {
            return null;
        } else {
            return
                    pm.getLaunchIntentForPackage(info.activityInfo.packageName);
        }
    }


    public static void logIntentException(String method, String pkgName, String activityName) {
        Log.e("IntentHelper",
                String.format(
                        "Couldn't get launch intent for:\n activity: %s\npackage: %s\n\t at %s() ",
                        nillStringOr(activityName),
                        nillStringOr(pkgName),
                        nillStringOr(method))
        );
    }

    public static void logIntentException(String method, ResolveInfo info) {
        logIntentException(method, info.activityInfo.packageName, info.activityInfo.name);
    }


    private static String nillStringOr(String string) {
        return string == null ? "null" : string;
    }

}