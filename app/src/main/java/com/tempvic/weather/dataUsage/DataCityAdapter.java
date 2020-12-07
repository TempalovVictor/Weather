package com.tempvic.weather.dataUsage;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.ICityItemCallback;
import com.tempvic.weather.R;

import java.util.ArrayList;

public class DataCityAdapter extends SimpleListAdapter {

    private SimpleListAdapter adapter;

    private final ICityItemCallback iCityItemInterface = new ICityItemCallback() {

        @Override
        public void onTriggerItem(int idItem) {
            ArrayList<IBaseListItem> items = adapter.getItems();
            for (int i = 0; i < items.size(); i++) {
                CityItem item = (CityItem) items.get(i);
                item.isSelected = idItem == item.idCityItem;
            }
            adapter.notifyDataSetChanged();
        }
    };

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == R.layout.item_city) {
            return new CityViewHolder(inflateByViewType(parent.getContext(), viewType, parent));
        } else {
            throw new IllegalStateException("Define new layout Id");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CityViewHolder) {
            TextView nameTextView = ((CityViewHolder) holder).nameTextView;
            CityItem item = (CityItem) getItems().get(position);
            nameTextView.setText(item.getText());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CityItem item = (CityItem) getItems().get(position);
                    //item.iCityItemInterface.onTriggerItem(item.idCityItem);

                    if (!item.isSelected()) {
                        v.setBackgroundColor(Color.LTGRAY);
                        item.setSelected(true);
                    } else {
                        v.setBackgroundColor(Color.TRANSPARENT);
                        item.setSelected(false);
                    }
                }
            });
        } else {
            throw new IllegalStateException("Define new layout Id");
        }
    }
}
