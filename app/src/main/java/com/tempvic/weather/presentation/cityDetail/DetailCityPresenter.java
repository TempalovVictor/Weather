package com.tempvic.weather.presentation.cityDetail;


import com.tempvic.weather.data.database.CitiesInfoTable;
import com.tempvic.weather.presentation.main.MainApplication;

import java.util.List;

public class DetailCityPresenter implements DetailCityContract.MvpPresenter{
    private DetailCityContract.MvpView contractView;
    private List<CitiesInfoTable> units;

    @Override
    public void onStart(DetailCityContract.MvpView contractView) {
        this.contractView = contractView;
        units = MainApplication.database.citiesInfoDao().getAll();
    }

    @Override
    public void onMvpViewContextCreated() {
        contractView.initUi();
        contractView.initAdapterWithDatabase(units);
    }

    @Override
    public void onClear() {
        contractView = null;
    }
}
