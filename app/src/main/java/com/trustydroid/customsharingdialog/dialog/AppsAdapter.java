package com.trustydroid.customsharingdialog.dialog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import com.trustydroid.customsharingdialog.R;


/**
 * Adapter for list of share-actions
 * */
class AppsAdapter extends RecyclerView.Adapter<ApplicationHolder> {

    private List<ApplicationEntry> items;
    private OnItemClickListener<ApplicationEntry> itemClickListener;


    public void setItems(List<ApplicationEntry> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addAll(Collection<ApplicationEntry> collection) {
        items.addAll(collection);
        notifyDataSetChanged();
    }

    public void add(ApplicationEntry item) {
        items.add(item);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public ApplicationEntry getItem(int position) {
        return items.get(position);
    }


    @Inject
    public AppsAdapter() {
    }


    @Override
    public ApplicationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ApplicationHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.share_dialog_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(ApplicationHolder holder, int position) {
        holder.bind(getItem(position));

        holder.itemView.setOnClickListener(v -> {
            ApplicationEntry item = getItem(position);
            if (itemClickListener != null) {
                itemClickListener.onItemClicked(item);
            }
        });
    }

    public void setItemClickListener(OnItemClickListener<ApplicationEntry> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public interface OnItemClickListener<T> {
        void onItemClicked(T item);
    }
}
