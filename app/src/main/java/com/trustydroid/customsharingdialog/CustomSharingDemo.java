package com.trustydroid.customsharingdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import com.trustydroid.customsharingdialog.dialog.ShareDialog;

/**
 * Entry point to demo app
 * */
public class CustomSharingDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_sharing_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.showMeTheDialog)
    void show(){
        ShareDialog.show(getSupportFragmentManager());
    }
}
