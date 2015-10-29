package com.trustydroid.customsharingdialog.dagger;


import dagger.Component;
import com.trustydroid.customsharingdialog.dialog.ShareDialog;

/**
 * Injects ShareDialog
 * */
@Component(modules = ShareDialogModule.class)
public interface ShareDialogComponent {
    void inject(ShareDialog dialog);
}
