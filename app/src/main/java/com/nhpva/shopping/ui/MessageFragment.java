package com.nhpva.shopping.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhpva.shopping.R;

public class MessageFragment extends Fragment {
    private static MessageFragment INSTANCE;

    public static MessageFragment getInstance(){
        if (INSTANCE == null){
            INSTANCE = new MessageFragment();
        }
        return INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }
}