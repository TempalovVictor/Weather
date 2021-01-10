package com.tempvic.weather.fragments;

import android.content.res.Configuration;
import android.graphics.Color;
import android.view.View;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment{

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

    public void checkThemeColor(View view){
        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                view.setBackgroundColor(Color.BLACK);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                view.setBackgroundColor(Color.WHITE);
                break;
        }
    }
}
