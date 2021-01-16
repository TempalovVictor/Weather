package com.tempvic.weather.presentation.cityList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tempvic.weather.presentation.base.NavigationHelper;
import com.tempvic.weather.presentation.cityList.item.ICityItemCallback;
import com.tempvic.weather.R;
import com.tempvic.weather.presentation.cityList.item.CityItem;
import com.tempvic.weather.presentation.cityList.item.DataCityAdapter;
import com.tempvic.weather.presentation.base.IBaseListItem;
import com.tempvic.weather.presentation.base.SimpleListAdapter;
import com.tempvic.weather.data.database.CitiesInfoTable;
import com.tempvic.weather.presentation.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import static com.tempvic.weather.common.Const.DEFAULT_TABLE_INT;

public class CityListFragment extends BaseFragment implements CityListContract.MvpView {

    private CityListContract.MvpPresenter presenter = new CityListPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onStart(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onMvpViewContextCreated();
    }

    @Override
    public void initUi() {
        //что нужно оставить тут?
    }

    @Override
    public void initAdapterWithDatabase(List<CitiesInfoTable> units) {
        RecyclerView recyclerView = getActivity().findViewById(R.id.rv_city_list);
        final ICityItemCallback iCityItemInterface = idItem -> handleSelectItems(recyclerView, idItem);
        DataCityAdapter adapter = new DataCityAdapter(iCityItemInterface);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        setListeners(adapter);

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
            new NavigationHelper().showDetailCityFragment(getActivity(), DEFAULT_TABLE_INT);
        });

        editButton.setOnClickListener(v -> {
            int selectedItem = findSelectedItemId(items);
            if (selectedItem != 0) {
                new NavigationHelper().showDetailCityFragment(getActivity(), selectedItem);
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
                    presenter.deleteCity(currentItem.idCityItem);
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

    @Override
    public void onBackPressedCallback() {
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, new Intent());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onClear();
        presenter = null;
    }
}