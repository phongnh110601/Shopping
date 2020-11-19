package com.nhpva.shopping.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhpva.shopping.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class CartActivity extends AppCompatActivity {
    private TextView tvTotal;
    private ImageView imgBack;
    private CircularProgressButton btnCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        changeBarColor();
        initViews();
        imgBack.setOnClickListener(view -> {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
        });
    }

    private void initViews() {
        tvTotal = findViewById(R.id.tv_total);
        imgBack = findViewById(R.id.img_back);
        btnCheckOut = findViewById(R.id.btn_check_out);
    }

    @SuppressLint("ObsoleteSdkInt")
    private void changeBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.nav_item_checked_color));
        }
    }
}