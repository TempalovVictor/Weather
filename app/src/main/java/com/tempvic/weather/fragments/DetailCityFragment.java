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
import com.tempvic.weather.dataUsage.DetailCityAdapter;
import com.tempvic.weather.dataUsage.DetailCityItem;
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

        MainApplication.database.citiesInfoDao().deleteAllData();
        new DatabaseHelper().fetchData();
        List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAll();
        String[] month = getResources().getStringArray(R.array.month);

        for (int i = 0; i < units.size(); i++) {
            CitiesInfoTable table = units.get(i);
            adapter.add(new DetailCityItem(month[i],table.getCityTemp()));
        }

        //String[] month = getResources().getStringArray(R.array.month);
        //final int countOfMonth = 12;

/*        TextView monthName = v.findViewById(R.id.tv_month_name);

        for (String s : month) {
            adapter.
        }*/
    }
}