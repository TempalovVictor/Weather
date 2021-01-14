package com.tempvic.weather.common;

import com.tempvic.weather.R;
import com.tempvic.weather.presentation.cityList.CityListFragment;

import androidx.fragment.app.FragmentActivity;

public final class NavigationHelper {

    public void showCityListFragment(FragmentActivity activity) {
        CityListFragment fragment = new CityListFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment_root, fragment, "CityListFragment")
                .addToBackStack("CityListFragment")
                .commit();

    }
}
