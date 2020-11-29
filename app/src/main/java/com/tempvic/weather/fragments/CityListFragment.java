package com.tempvic.weather.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class CityListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = getActivity().findViewById(R.id.rv_city_list);
        DataCityAdapter adapter = new DataCityAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        MainApplication.database.citiesInfoDao().deleteAllData();
        new DatabaseHelper().fetchData();
        List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAll();

        for (int i = 0; i < units.size(); i++) {
            CitiesInfoTable table = units.get(i);
            adapter.add(new CityItem(table.getCityName()));
        }
    }
}