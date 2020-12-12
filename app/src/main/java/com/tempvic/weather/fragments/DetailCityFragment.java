package com.tempvic.weather.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.MainApplication;
import com.tempvic.weather.R;
import com.tempvic.weather.dataUsage.CityItem;
import com.tempvic.weather.dataUsage.DetailCityAdapter;
import com.tempvic.weather.dataUsage.DetailCityItem;
import com.tempvic.weather.database.CitiesInfoTable;

import java.util.ArrayList;
import java.util.List;

public class DetailCityFragment extends Fragment {

    public int getCityId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getCityId = getArguments().getInt("ID", 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_city, container, false);
        //
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText etCityName = view.findViewById(R.id.et_city_name);

        RecyclerView recyclerView = getActivity().findViewById(R.id.rv_month_temp);
        DetailCityAdapter adapter = new DetailCityAdapter();



/*        List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAllByID(idCity);
        CitiesInfoTable table = units.get(0);
        CityItem item = new CityItem(table.getCityName(), table.getCityId());
        adapter.add(item);*/

        //ArrayList<CitiesInfoTable> dataForEdit = new ArrayList<>();
        //MainApplication.database.citiesInfoDao().getDataForEdit(idCity);
        //TODO Продолжить здесь

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        String[] month = getResources().getStringArray(R.array.month);

        if (getCityId != 0) {
            List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAllByID(getCityId);
            for (int i = 0; i < units.size(); i++) {
                CitiesInfoTable table = units.get(i);
                etCityName.setText(table.getCityName());
                String cityName = etCityName.toString();
                String[] temp = table.getMonthTemp();
                for (int с = 0; с < 12; с++) {
                    adapter.add(new DetailCityItem(month[с], temp[с]));
                }
/*                CitiesInfoTable citiesInfoTable = new CitiesInfoTable(cityName,
                        //cityTempScale,
                        temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7],
                        temp[8], temp[9], temp[10], temp[11]);

                MainApplication.database.citiesInfoDao().insert(citiesInfoTable);*/
            }
        }

        for (int i = 0; i < 12; i++) {
            adapter.add(new DetailCityItem(month[i]));
        }

        Button button = view.findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] temps = new String[12];
                //Spinner spinner = view.findViewById(R.id.sp_temp_scale);
                //EditText editText = view.findViewById(R.id.et_city_name);

                String cityName = etCityName.getText().toString();
                //String cityTempScale = spinner.getSelectedItem().toString();

                //CitiesInfoTable citiesInfoTable = new CitiesInfoTable(cityName);
                List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAll();

                ArrayList<String> cities = new ArrayList<>();

                for (int i = 0; i < units.size(); i++) {
                    CitiesInfoTable table = units.get(i);
                    String tableCityName = table.getCityName();
                    cities.add(tableCityName);
                }

                if (cities.contains(cityName) && getCityId == 0) {
                    Toast toast = Toast.makeText(getContext(),
                            "Ошибка! Город уже добавлен.", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (cityName.equals("")) {
                    Toast toast = Toast.makeText(getContext(),
                            "Ошибка! Введите название города.", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (cities.contains(cityName) && getCityId != 0) {
                    for (int i = 0; i < recyclerView.getChildCount(); i++) {
                        EditText et2 = recyclerView.getChildAt(i).findViewById(R.id.et_month_temp);
                        String temperature = et2.getText().toString();
                        temps[i] = temperature;
                    }

                    boolean tempEmpty = false;

                    for (String temp : temps) {
                        if (temp == null || temp.isEmpty()) {
                            Toast toast = Toast.makeText(getContext(),
                                    "Ошибка! Заполните значения температур для каждового месяца.", Toast.LENGTH_SHORT);
                            toast.show();
                            tempEmpty = true;
                            break;
                        }
                    }

                    if (!tempEmpty) {
                        CitiesInfoTable citiesInfoTable = new CitiesInfoTable(cityName,
                                //cityTempScale,
                                temps[0], temps[1], temps[2], temps[3], temps[4], temps[5], temps[6], temps[7],
                                temps[8], temps[9], temps[10], temps[11]);
                        //TODO Update не работает
                        MainApplication.database.citiesInfoDao().update(citiesInfoTable);

                        Toast toast = Toast.makeText(getContext(),
                                "Изменения внесены!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    for (int i = 0; i < recyclerView.getChildCount(); i++) {
                        EditText et2 = recyclerView.getChildAt(i).findViewById(R.id.et_month_temp);
                        String temperature = et2.getText().toString();
                        temps[i] = temperature;
                    }

                    boolean tempEmpty = false;

                    for (String temp : temps) {
                        if (temp == null || temp.isEmpty()) {
                            Toast toast = Toast.makeText(getContext(),
                                    "Ошибка! Заполните значения температур для каждового месяца.", Toast.LENGTH_SHORT);
                            toast.show();
                            tempEmpty = true;
                            break;
                        }
                    }

                    if (!tempEmpty) {
                        CitiesInfoTable citiesInfoTable = new CitiesInfoTable(cityName,
                                //cityTempScale,
                                temps[0], temps[1], temps[2], temps[3], temps[4], temps[5], temps[6], temps[7],
                                temps[8], temps[9], temps[10], temps[11]);

                        MainApplication.database.citiesInfoDao().insert(citiesInfoTable);

                        Toast toast = Toast.makeText(getContext(),
                                "Город добавлен!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }

    public String celsiusToFahrenheit(double celsius) {
        double convert = (celsius * 9 / 5) + 32;
        return String.valueOf(convert);
    }

    public String celsiusToKelvin(double celsius) {
        double convert = celsius + 273.15;
        return String.valueOf(convert);
    }

    public String fahrenheitToCelsius(double fahrenheit) {
        double convert = (fahrenheit - 32) * 5 / 9;
        return String.valueOf(convert);
    }

    public String fahrenheitToKelvin(double fahrenheit) {
        double convert = (fahrenheit - 32) * 5 / 9 + 273.15;
        return String.valueOf(convert);
    }

    public String kelvinToCelsius(double kelvin) {
        double convert = kelvin - 273.15;
        return String.valueOf(convert);
    }

    public String kelvinToFahrenheit(double kelvin) {
        double convert = (kelvin - 273.15) * 9 / 5 + 32;
        return String.valueOf(convert);
    }
}