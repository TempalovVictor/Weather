package com.tempvic.weather;

import com.tempvic.weather.database.CitiesInfoTable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    public void fetchData() {

        initCities();
    }

    private void initCities() {
        if (MainApplication.database.citiesInfoDao().getAll().size() == 0) {
            ArrayList<CitiesInfoTable> newItems = new ArrayList<>();

            newItems.add(new CitiesInfoTable(1, "Екатеринбург", "Малый",
                    "-20", "-30", "-15", "-5",
                    "10", "25", "30", "20",
                    "10", "5", "-10", "-20"));

            newItems.add(new CitiesInfoTable(2, "Москва", "Малый",
                    "-20", "-30", "-15", "-5",
                    "10", "25", "30", "20",
                    "10", "5", "-10", "-20"));

            newItems.add(new CitiesInfoTable(3, "Волгоград", "Малый",
                    "-20", "-30", "-15", "-5",
                    "10", "25", "30", "20",
                    "10", "5", "-10", "-20"));

            MainApplication.database.citiesInfoDao().insertList(newItems);
        }
    }
}
