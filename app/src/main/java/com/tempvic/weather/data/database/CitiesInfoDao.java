package com.tempvic.weather.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CitiesInfoDao {
    @Query("SELECT * FROM CitiesInfoTable")
    List<CitiesInfoTable> getAll();

    @Query("SELECT * FROM CitiesInfoTable WHERE cityId = :id")
    CitiesInfoTable getById(long id);

    @Query("SELECT cityType FROM CitiesInfoTable WHERE cityName = :cityName")
    String getCityTypeByName(String cityName);

    @Query("SELECT cityTempInDec, cityTempInJan, cityTempInFeb," +
            " cityTempInMar, cityTempInApr, cityTempInMay, " +
            "cityTempInJun, cityTempInJul, cityTempInAug," +
            " cityTempInSept, cityTempInOct, cityTempInNov FROM CitiesInfoTable WHERE cityName = :cityName")
    TempsByMonth getTempsByMonth(String cityName);

    @Query("SELECT cityId FROM CitiesInfoTable WHERE UPPER(cityName) = UPPER(:cityName)")
    int getId(String cityName);

    @Query("DELETE FROM CitiesInfoTable WHERE cityId = :id")
    void deleteByCityId(long id);

    @Query("DELETE FROM CitiesInfoTable")
    void deleteAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CitiesInfoTable citiesInfoTable);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(Iterable<CitiesInfoTable> citiesInfoTables);

    @Delete
    void delete(CitiesInfoTable citiesInfoTable);

}