package com.nhpva.shopping.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhpva.shopping.R;

public class NotificationFragment extends Fragment {
    private static NotificationFragment INSTANCE;

    public static NotificationFragment getInstance(){
        if (INSTANCE == null){
            INSTANCE = new NotificationFragment();
        }
        return INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }
}