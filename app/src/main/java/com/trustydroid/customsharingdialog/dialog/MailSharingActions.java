package com.trustydroid.customsharingdialog.dialog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.trustydroid.customsharingdialog.R;

/**
 * Utility to get actions for e-mail sharing
 */
class MailSharingActions {


    final Context context;

    @Inject
    MailSharingActions(Context context) {
        this.context = context;
    }

    /**
     * Returns a list of Share-actions based on available e-mail clients installed on device
     * */
    public List<ApplicationEntry> getMailActions() {
        List<ApplicationEntry> list = new ArrayList<>();
        //the apps we're looking for should respond to that kind of intent
        Intent queryIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));


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
            intent.setComponent(new ComponentName(info.activityInfo.packageName, info.activityInfo.name));

            //populate with data
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.subject));
            intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(context.getString(R.string.htmlText)));

            Drawable icon = info.loadIcon(pm);
            CharSequence label = info.loadLabel(pm);

            list.add(new ApplicationEntry(intent, icon, label));
        }


        return list;
    }


}
