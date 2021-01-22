package com.tempvic.weather.data.database;

import com.tempvic.weather.presentation.main.MainApplication;

import java.util.ArrayList;

public class DatabaseHelper {
    public void fetchData() {

        initCities();
    }

    private void initCities() {
        if (MainApplication.database.citiesInfoDao().getAll().size() == 0) {
            ArrayList<CitiesInfoTable> newItems = new ArrayList<>();

            newItems.add(new CitiesInfoTable(1, "Екатеринбург", "Средний",
                    "3", "4", "4", "5",
                    "6", "7", "8", "9",
                    "10", "11", "12", "1", 56.50,60.35));

            newItems.add(new CitiesInfoTable(2, "Москва", "Большой",
                    "14", "15", "16", "17",
                    "18", "19", "20", "21",
                    "22", "23", "24", "13", 55.45, 37.36));

            newItems.add(new CitiesInfoTable(3, "Нижняя Салда", "Малый",
                    "26", "27", "28", "29",
                    "30", "31", "32", "33",
                    "34", "35", "36", "25",58.4,60.42));

            MainApplication.database.citiesInfoDao().insertList(newItems);
        }
    }
}
