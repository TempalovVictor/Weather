package com.tempvic.weather.presentation.cityDetail;

import com.tempvic.weather.data.database.CitiesInfoTable;

import java.util.List;

public final class DetailCityContract {
    public interface MvpView {
        void initUi();

        void initAdapterWithDatabase(List<CitiesInfoTable> units);
    }

    public interface MvpPresenter {
        void onStart(DetailCityContract.MvpView contractView);

        void onMvpViewContextCreated();

        void onClear();
    }
}
