package com.trustydroid.customsharingdialog.dialog;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import com.trustydroid.customsharingdialog.R;


/**
 * used to launch an app from ApplicationEntry or show the error
 * */
class AppNavigator {

    final Context context;
    final FragmentManager fm;

    @Inject
    public AppNavigator(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
    }

    void openShareItem(ApplicationEntry entry) {
        Intent sharingIntent = entry.sharingIntent;
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (sharingIntent != null) {
            context.startActivity(sharingIntent);
        } else {
            SimpleAlertDialog.show(fm,
                    context.getString(R.string.dialog_title_error),
                    context.getString(R.string.no_app_found)
            );
        }
    }
}
