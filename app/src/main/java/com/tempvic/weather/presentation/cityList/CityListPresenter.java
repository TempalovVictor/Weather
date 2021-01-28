package com.tempvic.weather.presentation.cityList;

import com.tempvic.weather.data.database.CitiesInfoTable;
import com.tempvic.weather.presentation.main.MainApplication;

import java.util.List;

public class CityListPresenter implements CityListContract.MvpPresenter {
    private CityListContract.MvpView contractView;
    private List<CitiesInfoTable> units;

    @Override
    public void onStart(CityListContract.MvpView contractView) {
        this.contractView = contractView;
        units = MainApplication.database.citiesInfoDao().getAll();
    }

    @Override
    public void onMvpViewContextCreated() {
        units = MainApplication.database.citiesInfoDao().getAll();
        contractView.initAdapterWithDatabase(units);
    }

    @Override
    public void deleteCity(int idCityItem) {
        MainApplication.database.citiesInfoDao().deleteByCityId(idCityItem);
    }

    @Override
    public void onClear() {
        contractView = null;
    }
}
