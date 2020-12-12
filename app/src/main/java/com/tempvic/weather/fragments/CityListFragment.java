package com.tempvic.weather.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.DatabaseHelper;
import com.tempvic.weather.ICityItemCallback;
import com.tempvic.weather.MainApplication;
import com.tempvic.weather.R;
import com.tempvic.weather.dataUsage.CityItem;
import com.tempvic.weather.dataUsage.DataCityAdapter;
import com.tempvic.weather.dataUsage.IBaseListItem;
import com.tempvic.weather.dataUsage.SimpleListAdapter;
import com.tempvic.weather.database.CitiesInfoTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CityListFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DatabaseHelper().fetchData();
        //TODO перенести в application
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = getActivity().findViewById(R.id.rv_city_list);
        Button deleteButton = getActivity().findViewById(R.id.btn_del_city);
        Button editButton = getActivity().findViewById(R.id.btn_edit_city);

        final ICityItemCallback iCityItemInterface = new ICityItemCallback() {

            @Override
            public void onTriggerItem(int idItem) {
                SimpleListAdapter adapter = (SimpleListAdapter) recyclerView.getAdapter();
                ArrayList<IBaseListItem> items = adapter.getItems();
                CityItem triggeredItem;
                for (int i = 0; i < items.size(); i++) {
                    CityItem currentItem = (CityItem) items.get(i);
                    if (currentItem.idCityItem == idItem) {
                        triggeredItem = currentItem;
                        triggeredItem.isSelected = !triggeredItem.isSelected;
                    } else {
                        currentItem.isSelected = false;
                    }
                }
                adapter.notifyDataSetChanged();
            }
        };

        DataCityAdapter adapter = new DataCityAdapter(iCityItemInterface);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        ArrayList<IBaseListItem> items = adapter.getItems();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < items.size(); i++) {
                    CityItem currentItem = (CityItem) items.get(i);
                    if (currentItem.isSelected) {
                        adapter.remove(i);
                        MainApplication.database.citiesInfoDao().deleteByCityId(currentItem.idCityItem);
                        break;
                    }
                }
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailCityFragment fragment = new DetailCityFragment();
                Bundle bundle = new Bundle();
                for (int i = 0; i < items.size(); i++) {
                    CityItem currentItem = (CityItem) items.get(i);
                    if (currentItem.isSelected) {
                        bundle.putInt("ID", currentItem.idCityItem);
                        fragment.setArguments(bundle);
                        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container_fragment_root, fragment, "DetailCityFragment")
                                .addToBackStack("DetailCityFragment")
                                .commit();
                        break;
                    }
                }

            }
        });

        //MainApplication.database.citiesInfoDao().deleteAllData();

        List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAll();
        for (int i = 0; i < units.size(); i++) {
            CitiesInfoTable table = units.get(i);
            CityItem item = new CityItem(table.getCityName(), table.getCityId());
            adapter.add(item);
        }
    }
}