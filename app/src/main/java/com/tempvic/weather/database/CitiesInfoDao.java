package com.tempvic.weather.database;

import android.widget.Spinner;

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

    @Query("SELECT cityType FROM CitiesInfoTable WHERE cityId = :id")
    String getCityTypeById(long id);

    @Query("SELECT cityId FROM CitiesInfoTable WHERE UPPER(cityName) = UPPER(:cityName)")
    int getId(String cityName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CitiesInfoTable citiesInfoTable);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(Iterable<CitiesInfoTable> citiesInfoTables);

    @Delete
    void delete(CitiesInfoTable citiesInfoTable);

    @Query("DELETE FROM CitiesInfoTable WHERE cityId = :id")
    void deleteByCityId(long id);

    @Query("DELETE FROM CitiesInfoTable")
    void deleteAllData();

    @Query("SELECT EXISTS(SELECT * FROM CitiesInfoTable WHERE UPPER(cityName) = UPPER(:cityCheck))")
    Boolean cityAlreadyExist(String cityCheck);
}