package com.tempvic.weather.presentation.base;

import android.os.Bundle;

import com.tempvic.weather.R;
import com.tempvic.weather.presentation.cityDetail.DetailCityFragment;
import com.tempvic.weather.presentation.cityList.CityListFragment;
import com.tempvic.weather.presentation.filter.FilterFragment;
import com.tempvic.weather.presentation.main.MainActivity;

import androidx.fragment.app.FragmentActivity;

import java.util.Objects;

import static com.tempvic.weather.presentation.cityDetail.DetailCityFragment.ARGS_DETAIL_CITY_ID;

public final class NavigationHelper {

    public void showCityListFragment(BaseFragment targetFragment, int reqCode) {
        CityListFragment fragment = new CityListFragment();
        fragment.setTargetFragment(targetFragment, reqCode);
        ((MainActivity) targetFragment.getActivity()).addFragmentToContainer(fragment);
    }

    public void showDetailCityFragment(FragmentActivity activity, int selectedItem) {
        DetailCityFragment fragment = new DetailCityFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_DETAIL_CITY_ID, selectedItem);
        fragment.setArguments(bundle);
        ((MainActivity) activity).addFragmentToContainer(fragment);
    }
}
