package com.trustydroid.customsharingdialog.dialog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.trustydroid.customsharingdialog.R;

/**
 * An utility to get intents for sharing via messenger
 */
class MessengerAction {

    final Context context;


    @Inject
    MessengerAction(Context context) {
        this.context = context;
    }

    /**
     * Returns a list of Share-actions with messenger-like behavior: twitter, sms, etc
     */
    public List<ApplicationEntry> getMessengerActions() {

        List<ApplicationEntry> list = new ArrayList<>();

        //the apps we're looking for should respond to that kind of intent
        Intent queryIntent = new Intent(Intent.ACTION_SEND);
        queryIntent.setType("text/plain");


        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(queryIntent, 0);

        //iterate over available apps and try to get "launch intent" for them, that can be
        //used to launch an app
        for (ResolveInfo info : resolveInfos) {
            //get a "good" intent to launch external app
            Intent intent = IntentHelper.getLaunchIntentFrom(pm, info);
            if (intent == null) {
                IntentHelper.logIntentException("getActionsList", info);
                continue;

            }

            //specify explicit activity
            intent.setAction(Intent.ACTION_SEND);
            intent.setComponent(new ComponentName(info.activityInfo.packageName, info.activityInfo.name));
            //add some extras to launch intent
            intent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.messenger_message));

            Drawable icon = info.loadIcon(pm);
            CharSequence label = info.loadLabel(pm);

            list.add(new ApplicationEntry(intent, icon, label));
        }


        return list;

    }

}
