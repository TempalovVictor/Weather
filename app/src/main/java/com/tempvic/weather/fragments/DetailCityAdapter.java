package com.tempvic.weather.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.R;

public class DetailCityAdapter extends RecyclerView.Adapter<DetailCityAdapter.DetailCityViewHolder> {

    private int numberMonths = 12;

    @NonNull
    @Override
    public DetailCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.month_and_temp;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        DetailCityViewHolder viewHolder = new DetailCityViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailCityViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return numberMonths;
    }

    class DetailCityViewHolder extends RecyclerView.ViewHolder {

        TextView monthNameView;
        EditText monthTempView;

        public DetailCityViewHolder(@NonNull View itemView) {
            super(itemView);

            monthNameView = itemView.findViewById(R.id.tv_month_name);
            monthTempView = itemView.findViewById(R.id.et_month_temp);
        }

        void bind(String listIndex) {
            monthTempView.setText(String.valueOf(listIndex));
        }


    }
}