package com.tempvic.weather.database;

import androidx.room.ColumnInfo;

public class WinterTemps {
    @ColumnInfo(name = "cityTempInDec")
    public String cityTempInDec;

    public String getCityTempInDec() {
        return cityTempInDec;
    }

    @ColumnInfo(name = "cityTempInJan")
    public String cityTempInJan;

    public String getCityTempInJan() {
        return cityTempInJan;
    }

    @ColumnInfo(name = "cityTempInFeb")
    public String cityTempInFeb;

    public String getCityTempInFeb() {
        return cityTempInFeb;
    }
}
