package com.tempvic.weather.presentation.cityDetail;

import android.app.Activity;
import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.presentation.main.MainActivity;
import com.tempvic.weather.presentation.main.MainApplication;
import com.tempvic.weather.R;
import com.tempvic.weather.presentation.cityDetail.item.DetailCityAdapter;
import com.tempvic.weather.presentation.cityDetail.item.DetailCityItem;
import com.tempvic.weather.presentation.base.IBaseListItem;
import com.tempvic.weather.data.database.CitiesInfoTable;
import com.tempvic.weather.presentation.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.tempvic.weather.common.Const.DEFAULT_STRING;
import static com.tempvic.weather.common.Const.DEFAULT_TABLE_INT;

public class DetailCityFragment extends BaseFragment implements DetailCityContract.MvpView {

    private DetailCityContract.MvpPresenter presenter = new DetailCityPresenter();

    public final static String ARGS_DETAIL_CITY_ID = "ARGS_DETAIL_CITY_ID";
    public int detailCityId;

    @Override
    public String getFragmentName() {
        return "DetailCityFragment";
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            detailCityId = getArguments().getInt(ARGS_DETAIL_CITY_ID, DEFAULT_TABLE_INT);
        }
        presenter.onStart(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onMvpViewContextCreated();
    }

    @Override
    public void initAdapterWithDatabase() {

        CitiesInfoTable cityDetail = presenter.getDetailsById(detailCityId);

        EditText cityName = getView().findViewById(R.id.et_city_name);
        EditText latitude = getView().findViewById(R.id.et_latitude);
        EditText longitude = getView().findViewById(R.id.et_longitude);
        Spinner cityType = getView().findViewById(R.id.sp_type_city);

        ArrayAdapter<?> adapterSpinner =
                ArrayAdapter.createFromResource(getContext(),
                        R.array.type_city,
                        android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityType.setAdapter(adapterSpinner);

        RecyclerView recyclerView = Objects.requireNonNull(getActivity()).findViewById(R.id.rv_month_temp);
        DetailCityAdapter adapter = new DetailCityAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        setCityInfo(cityName, latitude, longitude, cityType);
        initItems(adapter, cityDetail);

        Button buttonSave = getView().findViewById(R.id.btn_save);
        buttonSave.setOnClickListener(v -> handleOnClickSave(cityName, latitude, longitude, cityType, recyclerView));
    }

    private void setCityInfo(EditText cityName, EditText latitude, EditText longitude, Spinner cityType) {
        if (detailCityId == DEFAULT_TABLE_INT) {
            cityName.setText(DEFAULT_STRING);
        } else {
            cityName.setText(presenter.getCityName());
            latitude.setText(presenter.getLatitude());
            longitude.setText(presenter.getLongitude());
            List<String> arrayType = Arrays.asList(getResources().getStringArray(R.array.type_city));
            String cityTypeFromDb = presenter.getCityType();
            int position = arrayType.indexOf(cityTypeFromDb);
            cityType.setSelection(position);
        }
    }

    private void initItems(DetailCityAdapter adapter, CitiesInfoTable cityDetail) {

        String[] monthArray = getResources().getStringArray(R.array.array_months);
        if (cityDetail == null || presenter.getMonthTemp().length == 0) {
            for (int i = 0; i < 12; i++) {
                adapter.add(new DetailCityItem(monthArray[i], DEFAULT_STRING));
            }
        } else {
            for (int i = 0; i < 12; i++) {
                adapter.add(new DetailCityItem(monthArray[i], presenter.getMonthTemp()[i]));
            }
        }
    }

    private void handleOnClickSave(EditText etCityName, EditText etLatitude, EditText etLongitude, Spinner spCityType, RecyclerView recyclerView) {

        String cityName = etCityName.getText().toString();
        String cityType = spCityType.getSelectedItem().toString();
        double latitude = Double.parseDouble(String.valueOf(etLatitude.getText()));
        double longitude = Double.parseDouble(String.valueOf(etLongitude.getText()));
//
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
                        allMonthItems.get(11).getTemperature(),
                        latitude,
                        longitude
                );
//
                MainApplication.database.citiesInfoDao().insert(citiesInfoTable);

                String completeMessage = detailCityId == DEFAULT_TABLE_INT ? "Новый город создан" : "Город обновлен";
                showMessage(completeMessage);

                if (detailCityId == DEFAULT_TABLE_INT) {
//
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

    //TODO вывести в пакет common метод ScreenNotice
    private void showMessage(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onClear();
        presenter = null;
    }
}