package com.tempvic.weather.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tempvic.weather.MainApplication;
import com.tempvic.weather.R;
import com.tempvic.weather.database.CitiesInfoTable;
import com.tempvic.weather.database.TempsByMonth;

import java.util.ArrayList;
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
                getAvgTemp(spCityName.getSelectedItem().toString(), spSeason.getSelectedItem().toString(), spTempScale.getSelectedItem().toString());
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
                getAvgTemp(spCityName.getSelectedItem().toString(), spSeason.getSelectedItem().toString(), spTempScale.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        getAvgTemp(spCityName.getSelectedItem().toString(), spSeason.getSelectedItem().toString(), spTempScale.getSelectedItem().toString());
    }

    private void getAvgTemp(String cityName, String season, String tempScale) {
        TextView tvAvgTemp = getView().findViewById(R.id.tv_value_temp_avg);


        TempsByMonth tempsByMonth = MainApplication.database.citiesInfoDao().getTempsByMonth(cityName);

        int tempInDec = Integer.parseInt(tempsByMonth.cityTempInDec);
        int tempInJan = Integer.parseInt(tempsByMonth.cityTempInJan);
        int tempInFeb = Integer.parseInt(tempsByMonth.cityTempInFeb);
        int tempInMar = Integer.parseInt(tempsByMonth.cityTempInMar);
        int tempInApr = Integer.parseInt(tempsByMonth.cityTempInApr);
        int tempInMay = Integer.parseInt(tempsByMonth.cityTempInMay);
        int tempInJun = Integer.parseInt(tempsByMonth.cityTempInJun);
        int tempInJul = Integer.parseInt(tempsByMonth.cityTempInJul);
        int tempInAug = Integer.parseInt(tempsByMonth.cityTempInAug);
        int tempInSept = Integer.parseInt(tempsByMonth.cityTempInSept);
        int tempInOct = Integer.parseInt(tempsByMonth.cityTempInOct);
        int tempInNov = Integer.parseInt(tempsByMonth.cityTempInNov);

        int avgTempInWinter = (tempInDec + tempInJan + tempInFeb) / 3;
        int avgTempInSpring = (tempInMar + tempInApr + tempInMay) / 3;
        int avgTempInSummer = (tempInJun + tempInJul + tempInAug) / 3;
        int avgTempInAutumn = (tempInSept + tempInOct + tempInNov) / 3;

        switch (season) {
            case "Весна":
                if(tempScale.equals("Фаренгейт")){
                    tvAvgTemp.setText(String.valueOf(celsiusToFahrenheit(avgTempInSpring)));
                } else if (tempScale.equals("Кельвин")){
                    tvAvgTemp.setText(String.valueOf(celsiusToKelvin(avgTempInSpring)));
                } else {
                    tvAvgTemp.setText(String.valueOf(avgTempInSpring));
                }
                break;
            case "Лето":
                if(tempScale.equals("Фаренгейт")){
                    tvAvgTemp.setText(String.valueOf(celsiusToFahrenheit(avgTempInSummer)));
                } else if (tempScale.equals("Кельвин")){
                    tvAvgTemp.setText(String.valueOf(celsiusToKelvin(avgTempInSummer)));
                } else {
                    tvAvgTemp.setText(String.valueOf(avgTempInSummer));
                }
                break;
            case "Осень":
                if(tempScale.equals("Фаренгейт")){
                    tvAvgTemp.setText(String.valueOf(celsiusToFahrenheit(avgTempInAutumn)));
                } else if (tempScale.equals("Кельвин")){
                    tvAvgTemp.setText(String.valueOf(celsiusToKelvin(avgTempInAutumn)));
                } else {
                    tvAvgTemp.setText(String.valueOf(avgTempInAutumn));
                }
                break;
            case "Зима":
                if(tempScale.equals("Фаренгейт")){
                    tvAvgTemp.setText(String.valueOf(celsiusToFahrenheit(avgTempInWinter)));
                } else if (tempScale.equals("Кельвин")){
                    tvAvgTemp.setText(String.valueOf(celsiusToKelvin(avgTempInWinter)));
                } else {
                    tvAvgTemp.setText(String.valueOf(avgTempInWinter));
                }
                break;
            default:
                break;
        }
    }

    private double celsiusToFahrenheit(int celsius) {
        return (double) (celsius * 9.0 / 5.0) + 32.0;
    }

    private double celsiusToKelvin(int celsius) {
        return (double) celsius + 273.15;
    }
}