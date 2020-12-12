package com.tempvic.weather.dataUsage;

import com.tempvic.weather.R;

public class DetailCityItem implements IBaseListItem {

    String month;
    String temperature;
    public Boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public DetailCityItem(String month, String temperature) {
        this.month = month;
        this.temperature = temperature;
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
