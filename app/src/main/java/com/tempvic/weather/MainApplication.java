package com.tempvic.weather;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.tempvic.weather.database.CitiesInfoDatabase;

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