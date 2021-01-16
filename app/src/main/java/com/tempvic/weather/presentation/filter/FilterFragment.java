package com.tempvic.weather.presentation.filter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.tempvic.weather.R;
import com.tempvic.weather.presentation.base.NavigationHelper;
import com.tempvic.weather.data.database.CitiesInfoTable;
import com.tempvic.weather.data.database.TempsByMonth;
import com.tempvic.weather.presentation.base.BaseFragment;
import com.tempvic.weather.presentation.main.MainApplication;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FilterFragment extends BaseFragment implements FilterContract.MvpView {

    private FilterContract.MvpPresenter presenter = new FilterPresenter();
    private final int REQ_CODE_SHOW_CITY_LIST = 1234;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onStart(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onMvpViewContextCreated();
    }

    @Override
    public void initUi() {
        Button btn = getView().findViewById(R.id.btn_edit);
        btn.setOnClickListener(v -> {
            new NavigationHelper().showCityListFragment(this, REQ_CODE_SHOW_CITY_LIST);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQ_CODE_SHOW_CITY_LIST) {
            presenter.updateDatabaseData();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void updateAdapterWithDatabase(List<CitiesInfoTable> units) {
        Spinner spCityName = getView().findViewById(R.id.sp_city);

        ArrayList<String> citiesName = new ArrayList<>();
        for (int i = 0; i < units.size(); i++) {
            CitiesInfoTable table = units.get(i);
            citiesName.add(table.getCityName());
        }

        ArrayAdapter<String> adapter = (ArrayAdapter<String>) spCityName.getAdapter();
        adapter.clear();
        adapter.addAll(citiesName);
    }

    @Override  //У нас тут вроде не только адаптеры, целесообразно ли так называть метод?
    public void initAdapterWithDatabase(List<CitiesInfoTable> units) {

        TextView tvAvgTemp = getView().findViewById(R.id.tv_value_temp_avg);
        Spinner spCityName = getView().findViewById(R.id.sp_city);
        Spinner spSeason = getView().findViewById(R.id.sp_season);
        Spinner spTempScale = getView().findViewById(R.id.sp_temp_scale);

        ArrayList<String> citiesName = new ArrayList<>();
        for (int i = 0; i < units.size(); i++) {
            CitiesInfoTable table = units.get(i);
            citiesName.add(table.getCityName());
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                citiesName
        );

        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCityName.setAdapter(adapterSpinner);


        spCityName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CitiesInfoTable selectedTable = null;
                for (CitiesInfoTable table : units) {
                    if (table.cityName.equals(spCityName.getSelectedItem().toString())) {
                        selectedTable = table;
                    }
                }

                if (selectedTable != null) {
                    TextView typeCity = getView().findViewById(R.id.tv_value_city_type);
                    typeCity.setText(selectedTable.getCityType());
                    setAvgTemp(spCityName.getSelectedItem().toString(), spSeason.getSelectedItem().toString(), spTempScale.getSelectedItem().toString(), tvAvgTemp);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSeason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (units.size() != 0) {
                    setAvgTemp(spCityName.getSelectedItem().toString(), spSeason.getSelectedItem().toString(), spTempScale.getSelectedItem().toString(), tvAvgTemp);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTempScale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (units.size() != 0) {
                    setAvgTemp(spCityName.getSelectedItem().toString(), spSeason.getSelectedItem().toString(), spTempScale.getSelectedItem().toString(), tvAvgTemp);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (units.size() != 0) {
            setAvgTemp(spCityName.getSelectedItem().toString(), spSeason.getSelectedItem().toString(), spTempScale.getSelectedItem().toString(), tvAvgTemp);
        }
    }

    private void setAvgTemp(String cityName, String season, String tempScale, TextView tvAvgTemp) {
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
                setAvgTemp(avgTempInSpring, tempScale, tvAvgTemp);
                break;
            case "Лето":
                setAvgTemp(avgTempInSummer, tempScale, tvAvgTemp);
                break;
            case "Осень":
                setAvgTemp(avgTempInAutumn, tempScale, tvAvgTemp);
                break;
            case "Зима":
                setAvgTemp(avgTempInWinter, tempScale, tvAvgTemp);
                break;
            default:
                break;
        }
    }

    private void setAvgTemp(int avgTemp, String tempScale, TextView tvAvgTemp) {
        if (tempScale.equals("Фаренгейт")) {
            tvAvgTemp.setText(String.valueOf(celsiusToFahrenheit(avgTemp)));
        } else if (tempScale.equals("Кельвин")) {
            tvAvgTemp.setText(String.valueOf(celsiusToKelvin(avgTemp)));
        } else {
            tvAvgTemp.setText(String.valueOf(avgTemp));
        }
    }

    private double celsiusToFahrenheit(int celsius) {
        return (double) (celsius * 9.0 / 5.0) + 32.0;
    }

    private double celsiusToKelvin(int celsius) {
        return (double) celsius + 273.15;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onClear();
        presenter = null;
    }
}