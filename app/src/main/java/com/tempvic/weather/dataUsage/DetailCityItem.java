package com.tempvic.weather.dataUsage;

import com.tempvic.weather.IDetailCityItemCallback;
import com.tempvic.weather.R;

public class DetailCityItem implements IBaseListItem{

    IDetailCityItemCallback iDetailCityItemInterface;
    String month;
    String temperature;
    public Boolean isSelected = false;
    public int idDetailCityItem;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public DetailCityItem(String month) {
        this.month = month;
    }

    public DetailCityItem(String month, String temperature) {
        this.month = month;
        this.temperature = temperature;
    }

    public DetailCityItem(String month, String temperature, int idDetailCityItem) {
        this.month = month;
        this.temperature = temperature;
        this.idDetailCityItem = idDetailCityItem;
    }

    public DetailCityItem(IDetailCityItemCallback iDetailCityItemInterface) {
        this.iDetailCityItemInterface = iDetailCityItemInterface;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_detail_city;
    }
}
