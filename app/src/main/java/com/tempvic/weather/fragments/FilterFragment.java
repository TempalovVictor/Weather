package com.tempvic.weather.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tempvic.weather.BuildConfig;
import com.tempvic.weather.MainApplication;
import com.tempvic.weather.R;
import com.tempvic.weather.dataUsage.CityItem;
import com.tempvic.weather.database.CitiesInfoTable;
import com.tempvic.weather.database.SpringTemps;
import com.tempvic.weather.database.WinterTemps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn = view.findViewById(R.id.btn_edit);
        btn.setOnClickListener(v -> {
            CityListFragment fragment = new CityListFragment();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_fragment_root, fragment, "CityListFragment")
                    .addToBackStack("CityListFragment")
                    .commit();
        });

        initUi();
    }

    private void initUi() {
        Spinner spCityName = getView().findViewById(R.id.sp_city);
        Spinner spSeason = getView().findViewById(R.id.sp_season);
        Spinner spTempScale = getView().findViewById(R.id.sp_temp_scale);

        List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAll();

        ArrayList<String> citiesName = new ArrayList<>();

        for (int i = 0; i < units.size(); i++) {
            CitiesInfoTable table = units.get(i);
            citiesName.add(table.getCityName());
        }

        ArrayAdapter<String> adapterSpinner =
                new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, citiesName);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCityName.setAdapter(adapterSpinner);

        String tempScaleStart = spTempScale.getSelectedItem().toString();
        String seasonStart = spSeason.getSelectedItem().toString();
        String cityNameStart = spCityName.getSelectedItem().toString();

        spCityName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String res = MainApplication.database.citiesInfoDao().getCityTypeByName(spCityName.getSelectedItem().toString());
                TextView typeCity = getView().findViewById(R.id.tv_value_city_type);
                typeCity.setText(res);
                String cityNameEnd = spCityName.getSelectedItem().toString();
                if (!cityNameStart.equals(cityNameEnd)) {
                }
                getAvgTemp(cityNameEnd, spSeason.getSelectedItem().toString(), spTempScale.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSeason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seasonEnd = spSeason.getSelectedItem().toString();
                if (!seasonStart.equals(seasonEnd)) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTempScale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tempScaleEnd = spTempScale.getSelectedItem().toString();
                if (!tempScaleStart.equals(tempScaleEnd)) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        getAvgTemp(spCityName.getSelectedItem().toString(), spSeason.getSelectedItem().toString(), spTempScale.getSelectedItem().toString());
    }

    private void getAvgTemp(String cityName, String season, String tempScale) {
        TextView tvAvgTemp = getView().findViewById(R.id.tv_value_temp_avg);


        WinterTemps winterTemps = MainApplication.database.citiesInfoDao().getTempInWinter(cityName);

        int tempInDec = Integer.parseInt(winterTemps.cityTempInDec);
        int tempInJan = Integer.parseInt(winterTemps.cityTempInJan);
        int tempInFeb = Integer.parseInt(winterTemps.cityTempInFeb);

        int tempInMar = Integer.parseInt(temps.cityTempInMar);
        int tempInApr = Integer.parseInt(temps.cityTempInApr);
        int tempInMay = Integer.parseInt(temps.cityTempInMay);
        int tempInJun = Integer.parseInt(temps.cityTempInJun);
        int tempInJul = Integer.parseInt(temps.cityTempInJul);
        int tempInAug = Integer.parseInt(temps.cityTempInAug);
        int tempInSept = Integer.parseInt(temps.cityTempInSept);
        int tempInOct = Integer.parseInt(temps.cityTempInOct);
        int tempInNov = Integer.parseInt(temps.cityTempInNov);

        int result = (tempInDec + tempInJan + tempInFeb)/3;

        tvAvgTemp.setText(String.valueOf(result));

        List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAll();

/*        ArrayList<String> tempInWinter = (ArrayList<String>) Collections.singletonList(MainApplication.database.citiesInfoDao().getTempInWinter(cityName));
        ArrayList<String> tempInSpring = (ArrayList<String>) Collections.singletonList(MainApplication.database.citiesInfoDao().getTempInSpring(cityName));
        ArrayList<String> tempInSummer = (ArrayList<String>) Collections.singletonList(MainApplication.database.citiesInfoDao().getTempInSummer(cityName));
        ArrayList<String> tempInAutumn = (ArrayList<String>) Collections.singletonList(MainApplication.database.citiesInfoDao().getTempInAutumn(cityName));*/

        //ArrayList<String> result = new ArrayList<>();

/*        switch (season){
            case "Зима":
        }*/

        ArrayList<String> citiesName = new ArrayList<>();

        for (int i = 0; i < units.size(); i++) {
            CitiesInfoTable table = units.get(i);
            citiesName.add(table.getCityName());
        }
    }
}