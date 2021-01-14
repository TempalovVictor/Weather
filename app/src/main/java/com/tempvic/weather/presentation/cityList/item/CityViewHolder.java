package com.tempvic.weather.presentation.cityList.item;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.R;

public class CityViewHolder extends RecyclerView.ViewHolder {

    TextView nameTextView;
    Spinner typeCity;

    public CityViewHolder(@NonNull View itemView) {
        super(itemView);

        nameTextView = itemView.findViewById(R.id.city_info_value);
        typeCity = itemView.findViewById(R.id.sp_type_city);
    }
}
