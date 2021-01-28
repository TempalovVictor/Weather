package com.tempvic.weather.presentation.cityDetail;

import com.tempvic.weather.data.database.CitiesInfoTable;

import java.util.List;

public final class DetailCityContract {
    public interface MvpView {

        void initAdapterWithDatabase();

    }

    public interface MvpPresenter {
        void onStart(DetailCityContract.MvpView contractView);

        void onMvpViewContextCreated();

        CitiesInfoTable getDetailsById(int id);

        String getCityName();

        String[] getMonthTemp();

        String getCityType();

        int getDetailCityId(String cityName);

        void insertData(CitiesInfoTable citiesInfoTable);

        void onClear();

        String getLatitude();

        String getLongitude();
    }
}
