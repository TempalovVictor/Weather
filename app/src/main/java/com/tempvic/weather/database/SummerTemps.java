package com.tempvic.weather.database;

import androidx.room.ColumnInfo;

class SummerTemps {
    @ColumnInfo(name = "cityTempInJun")
    public String cityTempInJun;

    @ColumnInfo(name = "cityTempInJul")
    public String cityTempInJul;

    @ColumnInfo(name = "cityTempInAug")
    public String cityTempInAug;
}
