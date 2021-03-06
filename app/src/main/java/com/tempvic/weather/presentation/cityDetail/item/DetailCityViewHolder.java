package com.tempvic.weather.presentation.cityDetail.item;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.R;

public class DetailCityViewHolder extends RecyclerView.ViewHolder {

    TextView monthNameView;
    EditText monthTempView;

    public DetailCityViewHolder(@NonNull View itemView) {
        super(itemView);

        monthNameView = itemView.findViewById(R.id.tv_month_name);
        monthTempView = itemView.findViewById(R.id.et_month_temp);
    }
}
