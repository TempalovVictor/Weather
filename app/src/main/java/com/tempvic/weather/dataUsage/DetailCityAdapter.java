package com.tempvic.weather.dataUsage;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.IDetailCityItemCallback;
import com.tempvic.weather.R;

import java.util.ArrayList;

public class DetailCityAdapter extends SimpleListAdapter {

    private static final int numberMonths = 12;

    private SimpleListAdapter adapter;

    private IDetailCityItemCallback iDetailCityItemInterface = new IDetailCityItemCallback() {

        @Override
        public void onTriggerItem(int idItem) {
            ArrayList<IBaseListItem> items = adapter.getItems();
            for (int i = 0; i < items.size(); i++) {
                DetailCityItem item = (DetailCityItem) items.get(i);
                item.isSelected = idItem == item.idDetailCityItem;
            }
            adapter.notifyDataSetChanged();
        }
    };

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == R.layout.item_detail_city) {
            return new DetailCityViewHolder(inflateByViewType(parent.getContext(), viewType, parent));
        } else {
            throw new IllegalStateException("Define new layout Id");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof DetailCityViewHolder) {
            TextView monthNameView = ((DetailCityViewHolder) holder).monthNameView;
            EditText monthTempView = ((DetailCityViewHolder) holder).monthTempView;
            DetailCityItem item = (DetailCityItem) getItems().get(position);
            monthNameView.setText(item.getMonth());
            monthTempView.setText(item.getTemperature());

/*            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DetailCityItem item = (DetailCityItem) getItems().get(position);
                    ArrayList<IBaseListItem> items = getItems();
                    for (int i = 0; i < items.size(); i++) {
                        if (item.isSelected = false) {
                            v.setBackgroundColor(Color.TRANSPARENT);
                        } else {
                            v.setBackgroundColor(Color.LTGRAY);
                            item.isSelected = false;
                        }
                    }
                }
            });*/
        }
        else {
            throw new IllegalStateException("Define new layout Id");
        }
    }

    @Override
    public int getItemCount() {
        return numberMonths;
    }
}