package com.tempvic.weather.database;

import androidx.room.ColumnInfo;

public class TempsByMonth {
    @ColumnInfo(name = "cityTempInDec")
    public String cityTempInDec;

    @ColumnInfo(name = "cityTempInJan")
    public String cityTempInJan;

    @ColumnInfo(name = "cityTempInFeb")
    public String cityTempInFeb;

    @ColumnInfo(name = "cityTempInMar")
    public String cityTempInMar;

    @ColumnInfo(name = "cityTempInApr")
    public String cityTempInApr;

    @ColumnInfo(name = "cityTempInMay")
    public String cityTempInMay;

    @ColumnInfo(name = "cityTempInJun")
    public String cityTempInJun;

    @ColumnInfo(name = "cityTempInJul")
    public String cityTempInJul;

    @ColumnInfo(name = "cityTempInAug")
    public String cityTempInAug;

    @ColumnInfo(name = "cityTempInSept")
    public String cityTempInSept;

    @ColumnInfo(name = "cityTempInOct")
    public String cityTempInOct;

    @ColumnInfo(name = "cityTempInNov")
    public String cityTempInNov;
}
