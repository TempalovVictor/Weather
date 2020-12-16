package com.tempvic.weather.database;

import androidx.room.ColumnInfo;

class AutumnTemps {
    @ColumnInfo(name = "cityTempInSept")
    public String cityTempInSept;

    @ColumnInfo(name = "cityTempInOct")
    public String cityTempInOct;

    @ColumnInfo(name = "cityTempInNov")
    public String cityTempInNov;
}
