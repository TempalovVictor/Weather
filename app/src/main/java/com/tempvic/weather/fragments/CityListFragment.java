package com.tempvic.weather.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import static com.tempvic.weather.Const.DEFAULT_TABLE_INT;
import static com.tempvic.weather.fragments.DetailCityFragment.ARGS_DETAIL_CITY_ID;

public class CityListFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();
    }

    private void initUi() {
        RecyclerView recyclerView = getActivity().findViewById(R.id.rv_city_list);
        final ICityItemCallback iCityItemInterface = idItem -> handleSelectItems(recyclerView, idItem);

        DataCityAdapter adapter = new DataCityAdapter(iCityItemInterface);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        setListeners(adapter);

        List<CitiesInfoTable> units = MainApplication.database.citiesInfoDao().getAll();
        for (int i = 0; i < units.size(); i++) {
            CitiesInfoTable table = units.get(i);
            CityItem item = new CityItem(table.getCityName(), table.getCityId());
            adapter.add(item);
        }
    }

    private void handleSelectItems(RecyclerView recyclerView, int idItem) {
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

    private void setListeners(DataCityAdapter adapter) {
        ArrayList<IBaseListItem> items = adapter.getItems();

        Button addCityButton = getActivity().findViewById(R.id.btn_add_city);
        Button deleteButton = getActivity().findViewById(R.id.btn_del_city);
        Button editButton = getActivity().findViewById(R.id.btn_edit_city);

        addCityButton.setOnClickListener(v -> {
            showDetailCityFragment(DEFAULT_TABLE_INT);
        });

        editButton.setOnClickListener(v -> {
            int selectedItem = findSelectedItemId(items);
            if(selectedItem != 0){
                showDetailCityFragment(selectedItem);
            } else {
                Toast toast = Toast.makeText(getContext(),
                        "Ошибка! Для редактирования выберите город из списка", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        deleteButton.setOnClickListener(v -> {
            for (int i = 0; i < items.size(); i++) {
                CityItem currentItem = (CityItem) items.get(i);
                if (currentItem.isSelected) {
                    adapter.remove(i);
                    MainApplication.database.citiesInfoDao().deleteByCityId(currentItem.idCityItem);
                    break;
                }
            }
        });
    }

    private int findSelectedItemId(ArrayList<IBaseListItem> items) {
        int selectedItem = DEFAULT_TABLE_INT;
        for (int i = 0; i < items.size(); i++) {
            CityItem currentItem = (CityItem) items.get(i);
            if (currentItem.isSelected) {
                selectedItem = currentItem.idCityItem;
                break;
            }
        }
        return selectedItem;
    }

    private void showDetailCityFragment(int selectedItem) {
        DetailCityFragment fragment = new DetailCityFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_DETAIL_CITY_ID, selectedItem);
        fragment.setArguments(bundle);
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment_root, fragment, "DetailCityFragment")
                .addToBackStack("DetailCityFragment")
                .commit();
    }
}