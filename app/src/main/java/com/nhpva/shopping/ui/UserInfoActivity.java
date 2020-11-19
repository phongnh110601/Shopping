package com.nhpva.shopping.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.nhpva.shopping.R;
import com.nhpva.shopping.viewmodel.UserViewModel;
import com.nhpva.shopping.viewmodel.UserViewModelFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoActivity extends AppCompatActivity {
    private static final int AVATAR_PICK = 100;
    private UserViewModel viewModel;
    private ProgressDialog dialog;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar = Calendar.getInstance();
    private CircularProgressButton btnSaveChange;
    private EditText edtUserName, edtUserPhoneNumber, edtUserDateOfBirth;
    private CircleImageView imgUserAvatar;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private RadioButton rbMale, rbFemale;
    private ImageView imgBack;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initViews();
        dialog = new ProgressDialog(this);
        viewModel = new ViewModelProvider(this, new UserViewModelFactory(getApplication())).get(UserViewModel.class);

        viewModel.getCurrentUser().observe(this, user -> {
            Glide.with(imgUserAvatar).load(user.getUserAvatar()).placeholder(R.mipmap.ic_launcher).into(imgUserAvatar);
            edtUserPhoneNumber.setText(user.getUserPhoneNumber());
            edtUserName.setText(user.getUserName());
            edtUserDateOfBirth.setText(user.getUserDateOfBirth());
            if (user.getUserSex().equals("male")){
                rbMale.setChecked(true);
            } else {
                rbFemale.setChecked(true);
            }
            dialog.dismiss();
        });
        viewModel.getCurrentUserData();

        btnSaveChange.setOnClickListener(view -> {
            dialog.setTitle("Saving changes");
            dialog.setMessage("Please wait for a minute!");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            String name = edtUserName.getText().toString().trim();
            String phoneNumber = edtUserPhoneNumber.getText().toString().trim();
            String dateOfBirth = edtUserDateOfBirth.getText().toString().trim();
            String sex = (rbMale.isChecked())? "male":"female";
            viewModel.changeCurrentUserData(name, phoneNumber, dateOfBirth, sex, uri);
        });


        edtUserDateOfBirth.setOnClickListener(view -> {
            datePickerDialog = new DatePickerDialog(this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        dateSetListener = (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateTime();
        };

        imgUserAvatar.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select image"), AVATAR_PICK);
        });

        imgBack.setOnClickListener(view -> {
            Intent intent = new Intent(UserInfoActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == AVATAR_PICK && resultCode == RESULT_OK && data != null){
            uri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgUserAvatar.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateTime() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat);
        edtUserDateOfBirth.setText(simpleDateFormat.format(calendar.getTime()));
    }

    private void initViews() {
        imgBack = findViewById(R.id.img_back);
        rbFemale = findViewById(R.id.rb_female);
        rbMale = findViewById(R.id.rb_male);
        btnSaveChange = findViewById(R.id.btn_save_change);
        edtUserDateOfBirth = findViewById(R.id.edt_user_date_of_birth);
        edtUserName = findViewById(R.id.edt_user_name);
        edtUserPhoneNumber = findViewById(R.id.edt_user_phone_number);
        imgUserAvatar = findViewById(R.id.img_user_avatar);
    }
}