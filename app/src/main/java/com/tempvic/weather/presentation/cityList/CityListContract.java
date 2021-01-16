package com.tempvic.weather.presentation.cityList;

import com.tempvic.weather.data.database.CitiesInfoTable;

import java.util.List;

public final class CityListContract {
    public interface MvpView {
        void initAdapterWithDatabase(List<CitiesInfoTable> units);
    }

    public interface MvpPresenter {
        void onStart(MvpView contractView);

        void onMvpViewContextCreated();

        void deleteCity(int idCityItem);

        void onClear();
    }
}
