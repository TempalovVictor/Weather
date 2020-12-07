package com.tempvic.weather.dataUsage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class SimpleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements IBaseListAdapter {
    private final ArrayList<IBaseListItem> items = new ArrayList();

    public final ArrayList<IBaseListItem> getItems() {
        return this.items;
    }

    public int getItemCount() {
        return this.items.size();
    }

    public int getItemViewType(int position) {
        IBaseListItem listItem;
        listItem = this.items.get(position);
        return listItem.getLayoutId();
    }

    protected final View inflateByViewType(@Nullable Context context, int viewType, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(viewType, parent, false);
    }

    public void add(IBaseListItem newItem) {
        this.items.add(newItem);
        this.notifyDataSetChanged();
    }

    public void add(ArrayList<IBaseListItem> newItems) {
        for (IBaseListItem newItem : newItems) {
            this.items.add(newItem);
            this.notifyDataSetChanged();
        }
    }

    public void addAtPosition(int pos, IBaseListItem newItem) {
        this.items.add(pos, newItem);
        this.notifyDataSetChanged();
    }

    public void clearAll() {
        this.items.clear();
        this.notifyDataSetChanged();
    }

    public void remove(int position) {
        this.items.remove(position);
        this.notifyDataSetChanged();
    }
}
