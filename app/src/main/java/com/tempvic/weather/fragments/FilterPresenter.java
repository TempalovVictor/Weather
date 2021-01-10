package com.tempvic.weather.fragments;

public class FilterPresenter implements FilterMvpPresenter{

    private V mFilterMvpView;

    @Override
    public void onViewAttach(MvpFilterView) {
        mFilterMvpView = MvpFilterView;
    }

    @Override
    public void onViewDetach() {
        mFilterMvpView = null;
    }

    @Override
    public void onCleared() {

    }
}
