package com.tempvic.weather.presentation.filter;

import com.tempvic.weather.data.database.CitiesInfoTable;
import com.tempvic.weather.presentation.main.MainApplication;

import java.util.List;

public class FilterPresenter implements FilterContract.MvpPresenter {
    private FilterContract.MvpView contractView;
    private List<CitiesInfoTable> units;

    @Override
    public void onStart(FilterContract.MvpView contractView) {
        this.contractView = contractView;
        units = MainApplication.database.citiesInfoDao().getAll();

    }

    @Override
    public void onMvpViewContextCreated() {
        contractView.initUi();
        contractView.initAdapterWithDatabase(units);
    }
}
