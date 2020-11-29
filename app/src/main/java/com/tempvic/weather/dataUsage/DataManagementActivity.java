package com.tempvic.weather.dataUsage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tempvic.weather.DatabaseHelper;
import com.tempvic.weather.MainApplication;
import com.tempvic.weather.R;
import com.tempvic.weather.database.CitiesInfoTable;

import java.util.ArrayList;
import java.util.List;

public class DataManagementActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_management);

        RecyclerView rV2 = findViewById(R.id.rv_list_of_city);
        DataCityAdapter adapter2 = new DataCityAdapter();
        rV2.setAdapter(adapter2);
        rV2.setLayoutManager(new LinearLayoutManager(this));

        MainApplication.database.citiesInfoDao().deleteAllData();
        new DatabaseHelper().fetchData();
        List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAll();

        for (int i = 0; i < units.size(); i++) {
            CitiesInfoTable table = units.get(i);
            adapter2.add(new CityItem(table.getCityName()));
        }
    }

/*    public void addNewCity(View view) {
        EditText etCity = findViewById(R.id.et_enter_city);
        String city = etCity.getText().toString();
        Boolean check = MainApplication.database.citiesInfoDao().cityAlreadyExist(city);

        if (TextUtils.isEmpty(etCity.getText())) {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_SHORT).show();
        } else {
            if (check) {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.empty_exist,
                        Toast.LENGTH_SHORT).show();
            } else {
                ArrayList<CitiesInfoTable> newCity = new ArrayList<>();
                newCity.add(new CitiesInfoTable(city));

                MainApplication.database.citiesInfoDao().insertList(newCity);

                RecyclerView rV2 = findViewById(R.id.rv_list_of_city);
                DataCityAdapter adapter2 = new DataCityAdapter();
                rV2.setAdapter(adapter2);
                rV2.setLayoutManager(new LinearLayoutManager(this));

                new DatabaseHelper().fetchData();
                List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAll();

                for (int i = 0; i < units.size(); i++) {
                    CitiesInfoTable table = units.get(i);
                    adapter2.add(new CityItem(table.getCityName()));
                }
            }
        }
    }*/
}
