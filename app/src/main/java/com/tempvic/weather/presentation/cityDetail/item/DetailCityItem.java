package com.tempvic.weather.presentation.cityDetail.item;

import com.tempvic.weather.R;
import com.tempvic.weather.presentation.base.IBaseListItem;

public class DetailCityItem implements IBaseListItem {

    String month;
    String temperature;

    public DetailCityItem(String month, String temperature) {
        this.month = month;
        this.temperature = temperature;
    }

    public String getMonth() {
        return month;
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
