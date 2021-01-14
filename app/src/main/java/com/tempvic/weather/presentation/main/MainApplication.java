package com.tempvic.weather.presentation.main;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.tempvic.weather.data.database.CitiesInfoDatabase;
import com.tempvic.weather.data.database.DatabaseHelper;

public class MainApplication extends Application {

    public static Context mainContext;
    public static CitiesInfoDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        mainContext = getApplicationContext();
        database = Room
                .databaseBuilder(
                        this,
                        CitiesInfoDatabase.class,
                        "cityInfoDatabase"
                )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        new DatabaseHelper().fetchData();
    }
}