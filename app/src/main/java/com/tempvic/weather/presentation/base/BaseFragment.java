package com.tempvic.weather.presentation.base;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public abstract String getFragmentName();

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
