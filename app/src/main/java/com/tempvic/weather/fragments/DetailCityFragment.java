package com.tempvic.weather.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.DatabaseHelper;
import com.tempvic.weather.MainApplication;
import com.tempvic.weather.R;
import com.tempvic.weather.dataUsage.CityItem;
import com.tempvic.weather.dataUsage.DetailCityAdapter;
import com.tempvic.weather.dataUsage.DetailCityItem;
import com.tempvic.weather.database.CitiesInfoTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailCityFragment extends Fragment {

    //private RecyclerView tempList;
    //String[] month = getResources().getStringArray(R.array.month);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_city, container, false);

        //View view = inflater.inflate(R.layout.fragment_detail_city, container, false);
        //tempList = view.findViewById(R.id.rv_month_temp);
        //tempList.setHasFixedSize(true);
        //tempList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //tempList.setAdapter(new DetailCityAdapter());
        //return view;

/*        for(int i = 0; i < month.length; i++) {
            textStrings.append("Name[" + i + "]: "+ month[i] + "\n");
        }*/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = getActivity().findViewById(R.id.rv_month_temp);
        DetailCityAdapter adapter = new DetailCityAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        //MainApplication.database.citiesInfoDao().deleteAllData();
        //new DatabaseHelper().fetchData();
        //List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAll();
        String[] month = getResources().getStringArray(R.array.month);

/*        for (int i = 0; i < 12; i++) {
            CitiesInfoTable table = units.get(i);
            adapter.add(new DetailCityItem(month[i],table.getCityTemp()));
        }*/

        for (int i = 0; i < 12; i++) {
            adapter.add(new DetailCityItem(month[i]));
        }

        //String[] month = getResources().getStringArray(R.array.month);
        //final int countOfMonth = 12;

/*        TextView monthName = v.findViewById(R.id.tv_month_name);

        for (String s : month) {
            adapter.
        }*/

/*        HashMap<Integer, String> passportsAndNames = new HashMap<>();
        String jun, feb, mar, apr, may, june, jul, aug, sep, oct, nov, dec;
        EditText field1 = (EditText)view.findViewById(R.id.et_month_temp);

        for (int i = 0; i < 12; i++) {

        }*/

        Button button = (Button) view.findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String[] temps  = new String[12];

                Spinner spinner = view.findViewById(R.id.sp_temp_scale);
                EditText editText = view.findViewById(R.id.et_city_name);

                String cityName = editText.getText().toString();
                String cityTempScale = spinner.getSelectedItem().toString();

                //CitiesInfoTable citiesInfoTable = new CitiesInfoTable(cityName);

                for(int i = 0; i <recyclerView.getChildCount(); i++) {
                    EditText et2 = recyclerView.getChildAt(i).findViewById(R.id.et_month_temp);
                    String temperature = et2.getText().toString();
                    temps[i] = temperature;
                }

                CitiesInfoTable citiesInfoTable = new CitiesInfoTable(cityName,cityTempScale,
                        temps[0],temps[1],temps[2],temps[3],temps[4],temps[5],temps[6],temps[7],
                        temps[8],temps[9],temps[10],temps[11]);

/*                Spinner spinner = view.findViewById(R.id.sp_temp_scale);
                EditText tempEt = view.findViewById(R.id.et_month_temp);

                EditText editText = view.findViewById(R.id.et_city_name);
                String cityName = editText.getText().toString();
                String cityTempScale = spinner.getSelectedItem().toString();
                String cityTemp = editText.getText().toString();
                CitiesInfoTable citiesInfoTable = new CitiesInfoTable(cityName,cityTempScale,cityTemp);*/


/*                if(cityName != null) {
                    newItems.add(new CitiesInfoTable(cityName));
                } else {
                    Toast toast = Toast.makeText(getContext(),
                            "cityName - пустое", Toast.LENGTH_SHORT);
                    toast.show();
                }*/
                MainApplication.database.citiesInfoDao().insert(citiesInfoTable);
            }
        });

        final Spinner tempScale = view.findViewById(R.id.sp_temp_scale);

        ArrayAdapter<?> adapterScale =
                ArrayAdapter.createFromResource(getContext(), R.array.scale_of_temp, android.R.layout.simple_spinner_item);
        adapterScale.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tempScale.setAdapter(adapterScale);

        tempScale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String[] choose = getResources().getStringArray(R.array.scale_of_temp);
                String startScale = choose[position];
/*                Toast toast = Toast.makeText(getContext(),
                        "Ваш выбор: " + choose[position], Toast.LENGTH_SHORT);
                toast.show();*/
/*                if(startScale.equals(choose[position])){
                    Toast toast = Toast.makeText(getContext(),
                            "из одной в туже" + choose[position], Toast.LENGTH_SHORT);
                    toast.show();
                } else if (!startScale.equals(choose[position]))*/

                if(choose[position].equals("Цельсий") && startScale.equals("Фаренгейт")) {
                    Toast toast = Toast.makeText(getContext(),
                            "из Ц в Ф" + choose[position], Toast.LENGTH_SHORT);
                    toast.show();
                } else if (choose[position].equals("Цельсий") && startScale.equals("Кельвин")) {
                    Toast toast = Toast.makeText(getContext(),
                            "из Ц в К" + choose[position], Toast.LENGTH_SHORT);
                    toast.show();
                } else if (choose[position].equals("Фаренгейт") && startScale.equals("Цельсий")) {
                    Toast toast = Toast.makeText(getContext(),
                            "из Ф в Ц" + choose[position], Toast.LENGTH_SHORT);
                    toast.show();
                } else if (choose[position].equals("Фаренгейт") && startScale.equals("Кельвин")) {
                    Toast toast = Toast.makeText(getContext(),
                            "из Ф в К" + choose[position], Toast.LENGTH_SHORT);
                    toast.show();
                } else if (choose[position].equals("Кельвин") && startScale.equals("Цельсий")) {
                    Toast toast = Toast.makeText(getContext(),
                            "из К в Ц" + choose[position], Toast.LENGTH_SHORT);
                    toast.show();
                }  else if (choose[position].equals("Кельвин") && startScale.equals("Фаренгейт")) {
                    Toast toast = Toast.makeText(getContext(),
                            "из К в Ф" + choose[position], Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getContext(),
                            "из одной в туже" + choose[position], Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }
}