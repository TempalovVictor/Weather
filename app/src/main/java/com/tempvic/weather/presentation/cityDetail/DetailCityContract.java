package com.tempvic.weather.presentation.cityDetail;

import com.tempvic.weather.data.database.CitiesInfoTable;

import java.util.List;

public final class DetailCityContract {
    public interface MvpView {

        void initAdapterWithDatabase(List<CitiesInfoTable> units);
    }

    public interface MvpPresenter {
        void onStart(DetailCityContract.MvpView contractView);

        void onMvpViewContextCreated();

        CitiesInfoTable getDetailsById(int id);

        void onClear();
    }
}
