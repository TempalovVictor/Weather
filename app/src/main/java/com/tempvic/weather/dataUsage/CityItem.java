package com.tempvic.weather.dataUsage;

import android.widget.Spinner;

import com.tempvic.weather.ICityItemCallback;
import com.tempvic.weather.R;

public class CityItem implements IBaseListItem {

    ICityItemCallback iCityItemInterface;
    String text;
    public Boolean isSelected = false;
    public int idCityItem;
    public Spinner cityType;

/*    public Spinner getCityType() {
        return cityType;
    }

    public void setCityType(Spinner cityType) {
        this.cityType = cityType;
    }

    public int getIdCityItem() {
        return idCityItem;
    }

    public void setIdCityItem(int idCityItem) {
        this.idCityItem = idCityItem;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public CityItem(String text) {
        this.text = text;
    }*/

    public CityItem(String text, int idCityItem) {
        this.text = text;
        this.idCityItem = idCityItem;
    }

/*    public CityItem(ICityItemCallback iCityItemInterface) {
        this.iCityItemInterface = iCityItemInterface;
    }*/

    public String getText() {
        return text;
    }

/*    public void setText(String text) {
        this.text = text;
    }*/

    @Override
    public int getLayoutId() {
        return R.layout.item_city;
    }
}
