package com.tempvic.weather.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tempvic.weather.R;

public class FilterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn = view.findViewById(R.id.btn_edit);
        btn.setOnClickListener(v -> {
            CityListFragment fragment = new CityListFragment();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_fragment_root, fragment, "CityListFragment")
                    .addToBackStack("CityListFragment")
                    .commit();

        });
    }

}