package com.tempvic.weather.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.tempvic.weather.R;
import com.tempvic.weather.fragments.CityListFragment;
import com.tempvic.weather.fragments.DetailCityFragment;
import com.tempvic.weather.fragments.FilterFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_fragment_root, new FilterFragment(), "FilterFragment")
                .addToBackStack("FilterFragment")
                .commit();

/*        RecyclerView recyclerView = findViewById(R.id.rvListOfCity);
        DataCityAdapter adapter = new DataCityAdapter();
        recyclerView.setAdapter(adapter);

        MainApplication.database.citiesInfoDao().deleteAllData();
        new DatabaseHelper().fetchData();
        List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAll();

        for (int i = 0; i < units.size(); i++) {
            CitiesInfoTable table = units.get(i);
            adapter.add(new CityItem(table.getCityName()));
        }*/

        Resources res = getResources();
        String[] month = res.getStringArray(R.array.month);
    }

    public void onClickEdit(View view) {
        CityListFragment fragment = new CityListFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment_root, fragment, "CityListFragment")
                .addToBackStack("CityListFragment")
                .commit();
    }

    public void onClickAddCity(View view) {
        DetailCityFragment fragment = new DetailCityFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment_root, fragment, "DetailCityFragment")
                .addToBackStack("DetailCityFragment")
                .commit();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0 || getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            //getSupportFragmentManager().popBackStack();
            super.onBackPressed();
        }
    }
}