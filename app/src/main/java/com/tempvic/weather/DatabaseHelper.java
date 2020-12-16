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
                    "3", "4", "4", "5",
                    "6", "7", "8", "9",
                    "10", "11", "12", "1"));

            newItems.add(new CitiesInfoTable(2, "Москва", "Средний",
                    "14", "15", "16", "17",
                    "18", "19", "20", "21",
                    "22", "23", "24", "13"));

            newItems.add(new CitiesInfoTable(3, "Волгоград", "Малый",
                    "26", "27", "28", "29",
                    "30", "31", "32", "33",
                    "34", "35", "36", "25"));

            MainApplication.database.citiesInfoDao().insertList(newItems);
        }
    }
}
