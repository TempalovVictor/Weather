package com.tempvic.weather.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.DatabaseHelper;
import com.tempvic.weather.MainApplication;
import com.tempvic.weather.R;
import com.tempvic.weather.dataUsage.CityItem;
import com.tempvic.weather.dataUsage.DataCityAdapter;
import com.tempvic.weather.database.CitiesInfoTable;

import java.util.List;

public class DetailCityFragment extends Fragment {

    //private RecyclerView tempList;
    //String[] month = getResources().getStringArray(R.array.month);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_city, container, false);

        //View view = inflater.inflate(R.layout.fragment_detail_city, container, false);
        //tempList = view.findViewById(R.id.rv_month_temp);
        //tempList.setHasFixedSize(true);
        //tempList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //tempList.setAdapter(new DetailCityAdapter());
        //return view;

/*        for(int i = 0; i < month.length; i++) {
            textStrings.append("Name[" + i + "]: "+ month[i] + "\n");
        }*/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = getActivity().findViewById(R.id.rv_month_temp);

        DetailCityAdapter adapter = new DetailCityAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

/*        LinearLayout linLayout = getActivity().findViewById(R.id.ll_month_and_temp);
        LayoutInflater ltInflater = getLayoutInflater();
        View v = ltInflater.inflate(R.layout.month_and_temp, linLayout, false);

        String[] month = getResources().getStringArray(R.array.month);
        TextView monthName = v.findViewById(R.id.tv_month_name);

        for (String s : month) {
            monthName.setText(s);
        }*/
    }
}