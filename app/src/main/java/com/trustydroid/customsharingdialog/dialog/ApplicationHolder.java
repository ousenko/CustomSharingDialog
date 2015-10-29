package com.trustydroid.customsharingdialog.dialog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.trustydroid.customsharingdialog.R;


/**
 * Represents application in list
 * */
class ApplicationHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.icon)
    ImageView icon;

    @Bind(R.id.text)
    TextView text;

    public ApplicationHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(ApplicationEntry item) {
        icon.setImageDrawable(item.icon);
        text.setText(item.title);
    }
}
