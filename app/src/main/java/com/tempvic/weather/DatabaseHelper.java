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

            newItems.add(new CitiesInfoTable(1, "Екатеринбург", "Большой",
                    "-20", "-30", "-15", "-5",
                    "10", "25", "30", "20",
                    "10", "5", "-10", "-10"));

            newItems.add(new CitiesInfoTable(2, "Москва", "Средний",
                    "2", "3", "-15", "-5",
                    "10", "25", "30", "20",
                    "10", "5", "-10", "1"));

            newItems.add(new CitiesInfoTable(3, "Волгоград", "Малый",
                    "4", "0", "-15", "-5",
                    "10", "25", "30", "20",
                    "10", "5", "-10", "-2"));

            MainApplication.database.citiesInfoDao().insertList(newItems);
        }
    }
}
