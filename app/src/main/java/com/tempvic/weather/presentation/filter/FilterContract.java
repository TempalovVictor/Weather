package com.tempvic.weather.presentation.filter;

import com.tempvic.weather.data.database.CitiesInfoTable;

import java.util.List;

public final class FilterContract {
    public interface MvpView {
        void initUi();

        void initAdapterWithDatabase(List<CitiesInfoTable> units);

        void updateAdapterWithDatabase(List<CitiesInfoTable> units);
    }

    public interface MvpPresenter {
        void onStart(MvpView contractView);

        void onMvpViewContextCreated();

        void onClear();

        void updateDatabaseData();
    }
}
