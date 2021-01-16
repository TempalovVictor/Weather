package com.tempvic.weather.common;

import android.os.Bundle;

import com.tempvic.weather.R;
import com.tempvic.weather.presentation.cityDetail.DetailCityFragment;
import com.tempvic.weather.presentation.cityList.CityListFragment;

import androidx.fragment.app.FragmentActivity;

import java.util.Objects;

import static com.tempvic.weather.presentation.cityDetail.DetailCityFragment.ARGS_DETAIL_CITY_ID;

public final class NavigationHelper {

    public void showCityListFragment(FragmentActivity activity) {
        CityListFragment fragment = new CityListFragment();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment_root, fragment, "CityListFragment")
                .addToBackStack("CityListFragment")
                .commit();
    }

    public void showDetailCityFragment(FragmentActivity activity, int selectedItem) {
            DetailCityFragment fragment = new DetailCityFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ARGS_DETAIL_CITY_ID, selectedItem);
            fragment.setArguments(bundle);
            Objects.requireNonNull(activity).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_fragment_root, fragment, "DetailCityFragment")
                    .addToBackStack("DetailCityFragment")
                    .commit();
        }
}
