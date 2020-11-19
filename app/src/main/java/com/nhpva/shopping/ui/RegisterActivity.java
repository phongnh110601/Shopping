package com.nhpva.shopping.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.nhpva.shopping.R;
import com.nhpva.shopping.viewmodel.UserViewModel;
import com.nhpva.shopping.viewmodel.UserViewModelFactory;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class RegisterActivity extends AppCompatActivity {

    private UserViewModel viewModel;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPhoneNumber;
    private EditText edtPassword;
    private CircularProgressButton btnRegister;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();
        initViews();
        dialog = new ProgressDialog(this);
        viewModel = new ViewModelProvider(this, new UserViewModelFactory(getApplication())).get(UserViewModel.class);
        viewModel.getRegisterSuccessfully().observe(this, registerSuccessfully -> {
            if (registerSuccessfully){
                dialog.dismiss();
                Toast.makeText(this, "Register successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                dialog.dismiss();
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
        btnRegister.setOnClickListener(view -> {
            dialog.setTitle("Signing up");
            dialog.setMessage("Please wait for a minute!");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            String name = edtName.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String phoneNumber = edtPhoneNumber.getText().toString().trim();
            viewModel.registerNewUserWithEmailAndPassword(RegisterActivity.this, name, email, password, phoneNumber);
        });

    }

    private void initViews() {
        edtEmail = findViewById(R.id.edt_email);
        edtName = findViewById(R.id.edt_name);
        edtPhoneNumber = findViewById(R.id.edt_phone_number);
        edtPassword = findViewById(R.id.edt_password);
        btnRegister = findViewById(R.id.btn_register);
    }

    @SuppressLint("ObsoleteSdkInt")
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
            window.setNavigationBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }


}