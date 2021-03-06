package com.tempvic.weather.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CitiesInfoTable.class}, version = 4, exportSchema = false)
public abstract class CitiesInfoDatabase extends RoomDatabase {
    public abstract CitiesInfoDao citiesInfoDao();
}
