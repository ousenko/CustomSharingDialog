package com.trustydroid.customsharingdialog.dialog;


import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Sharing dialog item model
 * */
class ApplicationEntry {

    Intent sharingIntent;
    Drawable icon;
    CharSequence title;


    public ApplicationEntry(Intent sharingIntent, Drawable icon, CharSequence title) {
        this.sharingIntent = sharingIntent;
        this.icon = icon;
        this.title = title;
    }

    /**
     * NOTE: we've overriden equals to get rid of the same apps used for Mail and Message share action
       * THIS IS NOT THE RECOMMENDED equals implementation, but it's not expected to cause any troubles
     * */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ApplicationEntry)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        ComponentName a = sharingIntent.getComponent();
        ComponentName b = ((ApplicationEntry) o).sharingIntent.getComponent();

        return a.equals(b);
    }

    /**
     * NOTE: we've overriden hashcode to get rid of the same apps used for Mail and Message share action
     * THIS IS NOT THE RECOMMENDED hashCode implementation, but I don't expect to have any troubles in this very use case
     * <br>If you want to know how to override hashCode & equals correctly, see
     * http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
     */
    @Override
    public int hashCode() {
        ComponentName component = sharingIntent.getComponent();
        return component.hashCode();
    }

    public CharSequence getPackageName() {
        return sharingIntent.getPackage();
    }

    public CharSequence getTitle() {
        return title;
    }

}
