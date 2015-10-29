package com.trustydroid.customsharingdialog.dagger;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;

/**
 * Provides ShareDialog dependencies
 * */
@Module
public class ShareDialogModule {

    final Fragment fragment;


    public ShareDialogModule(Fragment fragment) {
        this.fragment = fragment;

    }

    @Provides
    Context context() {
        return fragment.getContext();
    }

    @Provides
    FragmentManager fm() {
        return fragment.getFragmentManager();
    }

}
