package com.trustydroid.customsharingdialog.dialog;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.trustydroid.customsharingdialog.R;
import com.trustydroid.customsharingdialog.dagger.DaggerShareDialogComponent;
import com.trustydroid.customsharingdialog.dagger.ShareDialogModule;


/**
 * Custom share dialog implementation
 * */
public class ShareDialog extends DialogFragment {

    @Inject
    AppsAdapter adapter;

    @Inject
    MailSharingActions mailSharingActions;

    @Inject
    MessengerAction messengerSharingActions;

    @Inject
    AppNavigator navigator;

    @Bind(R.id.list)
    RecyclerView list;


    @OnClick(R.id.cancel)
    void onCancel() {
        dismiss();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View root = inflater.inflate(R.layout.fragment_share_dialog, container, false);
        ButterKnife.bind(this, root);

        inject();//dependencies

        //init list
        list.setLayoutManager(new WrapContentLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        list.setAdapter(adapter);
        adapter.setItemClickListener(navigator::openShareItem);
        //add content
        init();


        return root;
    }


    protected void inject() {
        DaggerShareDialogComponent.builder()
                .shareDialogModule(new ShareDialogModule(this))
                .build()
                .inject(this);

    }


    /**
     * insert apps into adapter and update view
     */
    protected void init() {

        List<ApplicationEntry> result;
        //we want to exclude duplicate apps by using ApplicationEntry#hashCode() and we don't care about sorting
        Set<ApplicationEntry> setOfItems = new HashSet<>();

        //we have a hacky hashcode implementation for ApplicationEntry
        //so that ApplicationEntry for the same app is only added ONCE.
        // some ApplicationEntries have the same app, but different action, so we want to avoid duplicates.
        // that is implementation specific - you may solve the problem of duplicate apps the other way.
        //so just notice this: mail sharing actions are added first.
        setOfItems.addAll(mailSharingActions.getMailActions());
        setOfItems.addAll(messengerSharingActions.getMessengerActions());


        result = new ArrayList<>(setOfItems);
        adapter.setItems(result);
    }


    public static void show(FragmentManager fm) {
        ShareDialog dialog = new ShareDialog();
        Bundle args = new Bundle();
        dialog.setArguments(args);
        dialog.show(fm, ShareDialog.class.getSimpleName());
    }


}
