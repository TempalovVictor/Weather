package com.tempvic.weather.dataUsage;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.ICityItemCallback;
import com.tempvic.weather.MainApplication;
import com.tempvic.weather.R;
import com.tempvic.weather.database.CitiesInfoTable;

import java.util.ArrayList;
import java.util.List;

public class DataCityAdapter extends SimpleListAdapter {

    private final ICityItemCallback iCityItemInterface;

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
            Spinner typeCity = ((CityViewHolder) holder).typeCity;
            int idCityItem = item.getIdCityItem();

            ArrayAdapter<?> adapter =
                    ArrayAdapter.createFromResource(((CityViewHolder) holder).typeCity.getContext(), R.array.type_city, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            typeCity.setAdapter(adapter);

            ((CityViewHolder) holder).typeCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String res = changeTypeCity(idCityItem);
                    item.setCityType(typeCity);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

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

    public String changeTypeCity (int idItem) {
        return MainApplication.database.citiesInfoDao().getCityTypeById(idItem);
    }
}
