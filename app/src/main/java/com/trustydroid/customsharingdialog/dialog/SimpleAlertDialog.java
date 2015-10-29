package com.trustydroid.customsharingdialog.dialog;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.trustydroid.customsharingdialog.R;


/**
 * An alert dialog implementation based on DialogFragment
 * */
public class SimpleAlertDialog extends DialogFragment {

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.message)
    TextView message;

    @OnClick({R.id.cancel, R.id.ok})
    void cancel() {
        dismiss();
    }

    @Nullable
    protected String getTitle() {
        return getType() == ArgType.RESOURCE ? getString(getIntFromArgs("title")) : getStringFromArgs("title");
    }

    @Nullable
    protected String getMessage() {
        return getType() == ArgType.RESOURCE ? getString(getIntFromArgs("message")) : getStringFromArgs("message");
    }

    private ArgType getType() {
        return (ArgType) getArguments().getSerializable("type");
    }

    @Nullable
    private String getStringFromArgs(String key) {
        return getArguments().getString(key);
    }

    private int getIntFromArgs(String key) {
        return getArguments().getInt(key, -1);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_alert_dialog, container, false);
        ButterKnife.bind(this, root);

        title.setText(getTitle());
        message.setText(getMessage());


        return root;
    }

    public static void show(FragmentManager fm, String title, String message) {
        Bundle args = new Bundle();
        args.putSerializable("type", ArgType.STRING);
        args.putString("title", title);
        args.putString("message", message);
        showInternal(fm, args);
    }


    public static void show(FragmentManager fm, @StringRes int title, @StringRes int message) {
        Bundle args = new Bundle();
        args.putSerializable("type", ArgType.RESOURCE);
        args.putInt("title", title);
        args.putInt("message", message);
        showInternal(fm, args);
    }

    private static void showInternal(FragmentManager fm, Bundle args) {
        SimpleAlertDialog dialog = new SimpleAlertDialog();
        dialog.setArguments(args);
        dialog.show(fm, SimpleAlertDialog.class.getSimpleName());
    }

    protected enum ArgType {
        STRING, RESOURCE
    }
}
