package com.tempvic.weather.presentation.cityDetail;


import com.tempvic.weather.data.database.CitiesInfoTable;
import com.tempvic.weather.presentation.main.MainApplication;

import java.util.List;

public class DetailCityPresenter implements DetailCityContract.MvpPresenter{
    private DetailCityContract.MvpView contractView;
    private List<CitiesInfoTable> units;
    private CitiesInfoTable cityDetail;

    @Override
    public void onStart(DetailCityContract.MvpView contractView) {
        this.contractView = contractView;
    }

    @Override
    public void onMvpViewContextCreated() {
        contractView.initAdapterWithDatabase(units);
    }

    @Override
    public CitiesInfoTable getDetailsById(int id){
        cityDetail = MainApplication.database.citiesInfoDao().getById(id);
        return cityDetail;
    }

    @Override
    public void onClear() {
        contractView = null;
    }
}
