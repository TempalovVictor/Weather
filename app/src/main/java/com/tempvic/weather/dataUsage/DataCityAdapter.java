package com.tempvic.weather.dataUsage;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.ICityItemCallback;
import com.tempvic.weather.R;

public class DataCityAdapter extends SimpleListAdapter {

    private final ICityItemCallback iCityItemInterface;

    //CTRL+ALT+O
    public DataCityAdapter(ICityItemCallback iCityItemInterface) {
        this.iCityItemInterface = iCityItemInterface;
    }

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
                    iCityItemInterface.onTriggerItem(item.idCityItem);
                }
            });
            if (item.isSelected) {
                holder.itemView.setBackgroundColor(Color.LTGRAY);
            } else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }
        } else {
            throw new IllegalStateException("Define new layout Id");
        }
    }
}
