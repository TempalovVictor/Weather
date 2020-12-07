package com.tempvic.weather.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class CitiesInfoTable {
    @PrimaryKey(autoGenerate = true)
    public int cityId;
    public String cityName;
    public String cityType;
    //public String cityTempScale;
    public String cityTempInJan;
    public String cityTempInFeb;
    public String cityTempInMar;
    public String cityTempInApr;
    public String cityTempInMay;
    public String cityTempInJun;
    public String cityTempInJul;
    public String cityTempInAug;
    public String cityTempInSept;
    public String cityTempInOct;
    public String cityTempInNov;
    public String cityTempInDec;

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String[] getMonthTemp() {

        return new String[]{
                cityTempInJan, cityTempInFeb,
                cityTempInMar, cityTempInApr,
                cityTempInMay, cityTempInJun,
                cityTempInJul, cityTempInAug,
                cityTempInSept, cityTempInOct,
                cityTempInNov, cityTempInDec
        };
    }

    /*    public String getCityTempScale() {
        return cityTempScale;
    }*/

    @Ignore
    public CitiesInfoTable(String cityName) {
        this.cityName = cityName;
    }

    @Ignore
    public CitiesInfoTable(String cityName,
                           //String cityTempScale,
                           String cityTempInJan,
                           String cityTempInFeb, String cityTempInMar, String cityTempInApr,
                           String cityTempInMay, String cityTempInJun, String cityTempInJul,
                           String cityTempInAug, String cityTempInSept, String cityTempInOct,
                           String cityTempInNov, String cityTempInDec) {
        this.cityName = cityName;
        //this.cityTempScale = cityTempScale;
        this.cityTempInJan = cityTempInJan;
        this.cityTempInFeb = cityTempInFeb;
        this.cityTempInMar = cityTempInMar;
        this.cityTempInApr = cityTempInApr;
        this.cityTempInMay = cityTempInMay;
        this.cityTempInJun = cityTempInJun;
        this.cityTempInJul = cityTempInJul;
        this.cityTempInAug = cityTempInAug;
        this.cityTempInSept = cityTempInSept;
        this.cityTempInOct = cityTempInOct;
        this.cityTempInNov = cityTempInNov;
        this.cityTempInDec = cityTempInDec;
    }

    public CitiesInfoTable(String cityName, String cityType,
                           //String cityTempScale,
                           String cityTempInJan,
                           String cityTempInFeb, String cityTempInMar, String cityTempInApr,
                           String cityTempInMay, String cityTempInJun, String cityTempInJul,
                           String cityTempInAug, String cityTempInSept, String cityTempInOct,
                           String cityTempInNov, String cityTempInDec) {
        this.cityName = cityName;
        this.cityType = cityType;
        //this.cityTempScale = cityTempScale;
        this.cityTempInJan = cityTempInJan;
        this.cityTempInFeb = cityTempInFeb;
        this.cityTempInMar = cityTempInMar;
        this.cityTempInApr = cityTempInApr;
        this.cityTempInMay = cityTempInMay;
        this.cityTempInJun = cityTempInJun;
        this.cityTempInJul = cityTempInJul;
        this.cityTempInAug = cityTempInAug;
        this.cityTempInSept = cityTempInSept;
        this.cityTempInOct = cityTempInOct;
        this.cityTempInNov = cityTempInNov;
        this.cityTempInDec = cityTempInDec;
    }

    @Ignore
    public CitiesInfoTable(int cityId, String cityName, String cityType, String cityTempInJan,
                           String cityTempInFeb, String cityTempInMar, String cityTempInApr,
                           String cityTempInMay, String cityTempInJun, String cityTempInJul,
                           String cityTempInAug, String cityTempInSept, String cityTempInOct,
                           String cityTempInNov, String cityTempInDec) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityType = cityType;
        this.cityTempInJan = cityTempInJan;
        this.cityTempInFeb = cityTempInFeb;
        this.cityTempInMar = cityTempInMar;
        this.cityTempInApr = cityTempInApr;
        this.cityTempInMay = cityTempInMay;
        this.cityTempInJun = cityTempInJun;
        this.cityTempInJul = cityTempInJul;
        this.cityTempInAug = cityTempInAug;
        this.cityTempInSept = cityTempInSept;
        this.cityTempInOct = cityTempInOct;
        this.cityTempInNov = cityTempInNov;
        this.cityTempInDec = cityTempInDec;
    }
}
