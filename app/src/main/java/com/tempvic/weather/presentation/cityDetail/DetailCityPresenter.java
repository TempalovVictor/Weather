package com.tempvic.weather.presentation.cityDetail;


import com.tempvic.weather.data.database.CitiesInfoDao;
import com.tempvic.weather.data.database.CitiesInfoTable;
import com.tempvic.weather.presentation.main.MainApplication;

import java.util.List;

public class DetailCityPresenter implements DetailCityContract.MvpPresenter{
    private DetailCityContract.MvpView contractView;
    private List<CitiesInfoTable> units;
    private CitiesInfoTable cityDetail;
    private CitiesInfoDao cityInfo;

    @Override
    public void onStart(DetailCityContract.MvpView contractView) {
        this.contractView = contractView;
        //units = MainApplication.database.citiesInfoDao().getAll();
    }

    @Override
    public void onMvpViewContextCreated() {
        contractView.initAdapterWithDatabase();
    }

    @Override
    public CitiesInfoTable getDetailsById(int id){
        cityDetail = MainApplication.database.citiesInfoDao().getById(id);
        return cityDetail;
    }

    @Override
    public String getCityName(){
        return cityDetail.getCityName();
    }

    @Override
    public String[] getMonthTemp(){
        return cityDetail.getMonthTemp();
    }

    @Override
    public String getCityType(){
        return cityDetail.getCityType();
    }

    @Override
    public int getDetailCityId(String cityName) {
        return cityInfo.getId(cityName);
    }

    @Override
    public void insertData(CitiesInfoTable citiesInfoTable) {
        cityInfo.insert(citiesInfoTable);
    }

    @Override
    public void onClear() {
        contractView = null;
    }

    @Override
    public String getLatitude() {
        return String.valueOf(cityDetail.getLatitude());
    }

    @Override
    public String getLongitude() {
        return String.valueOf(cityDetail.getLongitude());
    }
}
