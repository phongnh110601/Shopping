package com.nhpva.shopping.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhpva.shopping.R;

public class GiftFragment extends Fragment {
    private static GiftFragment INSTANCE;

    public static GiftFragment getInstance(){
        if (INSTANCE == null){
            INSTANCE = new GiftFragment();
        }
        return INSTANCE;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gift, container, false);
    }
}