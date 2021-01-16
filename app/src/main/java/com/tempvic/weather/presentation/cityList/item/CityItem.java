package com.tempvic.weather.presentation.cityList.item;

import com.tempvic.weather.R;
import com.tempvic.weather.presentation.base.IBaseListItem;

public class CityItem implements IBaseListItem {

    String text;
    public Boolean isSelected = false;
    public int idCityItem;

    public CityItem(String text, int idCityItem) {
        this.text = text;
        this.idCityItem = idCityItem;
    }

    public String getText() {
        return text;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_city;
    }
}
