package com.nhpva.shopping.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nhpva.shopping.R;
import com.nhpva.shopping.viewmodel.UserViewModel;
import com.nhpva.shopping.viewmodel.UserViewModelFactory;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import de.hdodenhof.circleimageview.CircleImageView;

public class AccountFragment extends Fragment {
    private UserViewModel userViewModel;
    private CircularProgressButton btnLogout;
    private ConstraintLayout layoutAddress, layoutUserInfo;
    private TextView tvUserName, tvUserEmail, tvUserPhoneNumber;
    private CircleImageView imgUserAvatar;
    private ProgressDialog dialog;
    private static AccountFragment INSTANCE;

    public static AccountFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountFragment();
        }
        return INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        initViews(view);
        dialog = new ProgressDialog(getContext());
        userViewModel = new ViewModelProvider(requireActivity(), new UserViewModelFactory(requireActivity().getApplication())).get(UserViewModel.class);
        userViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            tvUserName.setText(user.getUserName());
            tvUserEmail.setText(user.getUserEmail());
            tvUserPhoneNumber.setText(user.getUserPhoneNumber());
            Glide.with(imgUserAvatar).load(user.getUserAvatar()).placeholder(R.mipmap.ic_launcher).into(imgUserAvatar);

        });
        userViewModel.getCurrentUserData();
        userViewModel.getLoginSuccessfully().observe(getViewLifecycleOwner(), loginSuccessfully -> {
            if (!loginSuccessfully) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(view1 -> {
            dialog.setTitle("Signing in");
            dialog.setMessage("Please wait for a minute!");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            userViewModel.logOutUser();
        });

        layoutUserInfo.setOnClickListener(view12 -> {
            Intent intent = new Intent(getActivity(), UserInfoActivity.class);
            startActivity(intent);
        });
        return view;

    }

    private void initViews(View view) {
        btnLogout = view.findViewById(R.id.btn_logout);
        layoutAddress = view.findViewById(R.id.layout_address);
        layoutUserInfo = view.findViewById(R.id.layout_user_info);
        tvUserName = view.findViewById(R.id.tv_user_name);
        tvUserEmail = view.findViewById(R.id.tv_user_email);
        tvUserPhoneNumber = view.findViewById(R.id.tv_user_phone_number);
        imgUserAvatar = view.findViewById(R.id.img_avatar);
    }
}