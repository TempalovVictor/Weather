package com.tempvic.weather.database;

import androidx.room.ColumnInfo;

class SpringTemps {
    @ColumnInfo(name = "cityTempInMar")
    public String cityTempInMar;

    public String getCityTempInMar() {
        return cityTempInMar;
    }

    @ColumnInfo(name = "cityTempInApr")
    public String cityTempInApr;

    public String getCityTempInApr() {
        return cityTempInApr;
    }

    @ColumnInfo(name = "cityTempInMay")
    public String cityTempInMay;

    public String getCityTempInMay() {
        return cityTempInMay;
    }
}
