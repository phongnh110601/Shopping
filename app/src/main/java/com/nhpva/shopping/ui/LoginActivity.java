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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhpva.shopping.R;
import com.nhpva.shopping.viewmodel.UserViewModel;
import com.nhpva.shopping.viewmodel.UserViewModelFactory;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class LoginActivity extends AppCompatActivity {
    private UserViewModel viewModel;
    private EditText edtEmail;
    private EditText edtPassword;
    private CheckBox cbRemember;
    private CircularProgressButton btnLogin;
    private ImageView imgFacebook;
    private ImageView imgGoogle;
    private TextView tvForgotPassword;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        changeBarColor();
        initViews();
        dialog = new ProgressDialog(this);
        viewModel = new ViewModelProvider(this, new UserViewModelFactory(getApplication())).get(UserViewModel.class);
        viewModel.getLoginSuccessfully().observe(this, loginSuccessfully -> {
            if (loginSuccessfully){
                dialog.dismiss();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                dialog.dismiss();
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
        btnLogin.setOnClickListener(view -> {
            dialog.setTitle("Signing in");
            dialog.setMessage("Please wait for a minute!");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            boolean remember = cbRemember.isChecked();
            viewModel.loginUserWithEmailAndPassword(LoginActivity.this, email, password, remember);
        });
    }

    private void initViews() {
        edtEmail = findViewById(R.id.edt_login_email);
        edtPassword = findViewById(R.id.edt_login_password);
        cbRemember = findViewById(R.id.cb_remember);
        btnLogin = findViewById(R.id.btn_login);
        imgFacebook = findViewById(R.id.img_facebook);
        imgGoogle = findViewById(R.id.img_google);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
    }

    @SuppressLint("ObsoleteSdkInt")
    private void changeBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.login_bk_color));
            window.setNavigationBarColor(getResources().getColor(R.color.login_bk_color));
        }
    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
}