package com.tempvic.weather.dataUsage;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.R;

public class CityViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView;

    public CityViewHolder(@NonNull View itemView) {
        super(itemView);

        nameTextView = itemView.findViewById(R.id.city_info_value);
    }
}
