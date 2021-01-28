package com.tempvic.weather.presentation.main;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.tempvic.weather.data.database.CitiesInfoDatabase;
import com.tempvic.weather.data.database.DatabaseHelper;
import com.tempvic.weather.presentation.filter.WeatherService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainApplication extends Application {

    public static Context mainContext;
    public static CitiesInfoDatabase database;
    private static WeatherService weatherService;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherService = retrofit.create(WeatherService.class);
    }

    public static WeatherService getWeatherService() {
        return weatherService;
    }
}