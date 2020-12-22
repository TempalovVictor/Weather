package com.tempvic.weather.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.MainApplication;
import com.tempvic.weather.R;
import com.tempvic.weather.dataUsage.DetailCityAdapter;
import com.tempvic.weather.dataUsage.DetailCityItem;
import com.tempvic.weather.dataUsage.IBaseListItem;
import com.tempvic.weather.database.CitiesInfoTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.tempvic.weather.Const.DEFAULT_STRING;
import static com.tempvic.weather.Const.DEFAULT_TABLE_INT;

public class DetailCityFragment extends Fragment {

    public final static String ARGS_DETAIL_CITY_ID = "ARGS_DETAIL_CITY_ID";
    public int detailCityId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            detailCityId = getArguments().getInt(ARGS_DETAIL_CITY_ID, DEFAULT_TABLE_INT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CitiesInfoTable cityDetail = MainApplication.database.citiesInfoDao().getById(detailCityId);
        EditText cityName = view.findViewById(R.id.et_city_name);
        Spinner cityType = view.findViewById(R.id.sp_type_city);

        ArrayAdapter<?> adapterSpinner =
                ArrayAdapter.createFromResource(getContext(), R.array.type_city, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cityType.setAdapter(adapterSpinner);

        setCityName(cityDetail, cityName, cityType);

        Button buttonSave = view.findViewById(R.id.btn_save);

        RecyclerView recyclerView = Objects.requireNonNull(getActivity()).findViewById(R.id.rv_month_temp);
        DetailCityAdapter adapter = new DetailCityAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        initItems(adapter, cityDetail);
        buttonSave.setOnClickListener(v -> handleOnClickSave(cityName, cityType, recyclerView));
    }

    private void setCityName(CitiesInfoTable units, EditText cityName, Spinner cityType) {
        if (detailCityId == DEFAULT_TABLE_INT) {
            cityName.setText(DEFAULT_STRING);
        } else {
            cityName.setText(units.getCityName());
            List<String> arrayType = Arrays.asList(getResources().getStringArray(R.array.type_city));
            String cityTypeFromDb = units.getCityType();
            int position = arrayType.indexOf(cityTypeFromDb);
            cityType.setSelection(position);
        }
    }

    private void initItems(DetailCityAdapter adapter, CitiesInfoTable units) {

        String[] monthArray = getResources().getStringArray(R.array.array_months);
        if (units == null || units.getMonthTemp().length == 0) {
            for (int i = 0; i < 12; i++) {
                adapter.add(new DetailCityItem(monthArray[i], DEFAULT_STRING));
            }
        } else {
            for (int i = 0; i < 12; i++) {
                adapter.add(new DetailCityItem(monthArray[i], units.getMonthTemp()[i]));
            }
        }
    }

    private void handleOnClickSave(EditText etCityName, Spinner spCityType, RecyclerView recyclerView) {

        String cityName = etCityName.getText().toString();
        String cityType = spCityType.getSelectedItem().toString();

        List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAll();

        ArrayList<String> cities = new ArrayList<>();

        for (int i = 0; i < units.size(); i++) {
            CitiesInfoTable table = units.get(i);
            String tableCityName = table.getCityName().replaceAll("[^a-zA-Z-а-яёА-ЯЁ]+", "").toUpperCase();
            cities.add(tableCityName);
        }

        if (TextUtils.isEmpty(cityName)) {
            showMessage("Ошибка! Введите название города");
        } else if (detailCityId == 0 && cities.contains(cityName.replaceAll("[^a-zA-Z-а-яёА-ЯЁ]+", "").toUpperCase())) {
            showMessage("Ошибка! Город с таким названием уже есть");
        } else {
            DetailCityAdapter adapter = (DetailCityAdapter) recyclerView.getAdapter();
            assert adapter != null;
            ArrayList<DetailCityItem> allMonthItems = tryGetAllMonthItems(adapter);

            for (int i = 0; i < allMonthItems.size(); i++) {
                if (allMonthItems.get(i).getTemperature().equals(".") ||
                        allMonthItems.get(i).getTemperature().equals("-") ||
                        allMonthItems.get(i).getTemperature().equals("-.") ||
                        allMonthItems.get(i).getTemperature().contains("-.")) {
                    Toast toast = Toast.makeText(getContext(), "Ошибка! Температура месяца " + allMonthItems.get(i).getMonth() + " заполнена неверно.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else if (allMonthItems.get(i).getTemperature().contains(".")) {
                    Toast toast = Toast.makeText(getContext(), "Ошибка! Температура месяца " + allMonthItems.get(i).getMonth() + " должна быть целым числом.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            }

            if (allMonthItems.size() == 0) {
                showMessage("Ошибка! Заполните значения температур для каждового месяца");
            } else {
                CitiesInfoTable citiesInfoTable = new CitiesInfoTable(
                        detailCityId,
                        cityName,
                        cityType,
                        allMonthItems.get(0).getTemperature(),
                        allMonthItems.get(1).getTemperature(),
                        allMonthItems.get(2).getTemperature(),
                        allMonthItems.get(3).getTemperature(),
                        allMonthItems.get(4).getTemperature(),
                        allMonthItems.get(5).getTemperature(),
                        allMonthItems.get(6).getTemperature(),
                        allMonthItems.get(7).getTemperature(),
                        allMonthItems.get(8).getTemperature(),
                        allMonthItems.get(9).getTemperature(),
                        allMonthItems.get(10).getTemperature(),
                        allMonthItems.get(11).getTemperature()
                );

                MainApplication.database.citiesInfoDao().insert(citiesInfoTable);

                String completeMessage = detailCityId == DEFAULT_TABLE_INT ? "Новый город создан" : "Город обновлен";
                showMessage(completeMessage);

                if (detailCityId == DEFAULT_TABLE_INT) {
                    detailCityId = MainApplication.database.citiesInfoDao().getId(cityName);
                }
            }
        }
    }

    private ArrayList<DetailCityItem> tryGetAllMonthItems(DetailCityAdapter adapter) {

        ArrayList<DetailCityItem> allItems = new ArrayList<>();

        boolean isTempEmpty = false;
        for (IBaseListItem item : adapter.getItems()) {
            if (TextUtils.isEmpty(((DetailCityItem) item).getTemperature())) {
                isTempEmpty = true;
                break;
            }
            allItems.add((DetailCityItem) item);
        }
        return isTempEmpty ? new ArrayList<>() : allItems;
    }

    private void showMessage(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}