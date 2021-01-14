package com.tempvic.weather.presentation.base;

public interface IBaseListAdapter {
    void add(IBaseListItem newItem);

//    void add(ArrayList<IBaseListItem> newItems);

//    void addAtPosition(int pos, IBaseListItem newItem);

    void remove(int position);

//    void clearAll();
}
