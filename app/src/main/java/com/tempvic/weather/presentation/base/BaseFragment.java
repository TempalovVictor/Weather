package com.tempvic.weather.presentation.base;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();
        //TODO здесь нужно будет вызвать onViewAttach
    }

    @Override
    public void onStop() {
        super.onStop();
        //TODO здесь нужно будет вызвать onViewDetach
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkThemeColor();
    }

    public void onBackPressedCallback() {
    }

    public void checkThemeColor() {
        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                Objects.requireNonNull(getView()).setBackgroundColor(Color.BLACK);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                Objects.requireNonNull(getView()).setBackgroundColor(Color.WHITE);
                break;
        }
    }
}
