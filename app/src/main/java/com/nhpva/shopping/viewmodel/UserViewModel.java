package com.nhpva.shopping.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nhpva.shopping.model.User;

import java.util.Calendar;
import java.util.HashMap;

public class UserViewModel extends ViewModel {
    private SharedPreferences sharedPreferences;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private MutableLiveData<Boolean> registerSuccessfully = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginSuccessfully = new MutableLiveData<>();
    private MutableLiveData<User> currentUser = new MutableLiveData<>();
    private User user;

    public UserViewModel(Application application) {
        sharedPreferences = application.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firebaseUser = firebaseAuth.getCurrentUser();
    }

    public MutableLiveData<Boolean> getRegisterSuccessfully(){
        return registerSuccessfully;
    }

    public MutableLiveData<Boolean> getLoginSuccessfully(){
        return loginSuccessfully;
    }

    public MutableLiveData<User> getCurrentUser(){
        return currentUser;
    }

    public void registerNewUserWithEmailAndPassword(Activity activity, String name, String email, String password, String phoneNumber){
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()){
            registerSuccessfully.postValue(false);
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, task -> {
                if (task.isSuccessful()){
                    FirebaseUser newUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = newUser.getUid();
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
                    String dateOfBirth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                            + "/" + Calendar.getInstance().get(Calendar.MONTH)
                            + "/" + Calendar.getInstance().get(Calendar.YEAR);
                    HashMap<String, String> userInfo = new HashMap<>();
                    userInfo.put("name", name);
                    userInfo.put("email", email);
                    userInfo.put("phone_number", phoneNumber);
                    userInfo.put("avatar", "default");
                    userInfo.put("sex", "male");
                    userInfo.put("date_of_birth", dateOfBirth);
                    databaseReference.setValue(userInfo);
                    user = new User(name, email, phoneNumber, "default", "male", dateOfBirth);
                    registerSuccessfully.postValue(true);
                } else {
                    registerSuccessfully.postValue(false);
                }
            });
        }
    }

    public void loginUserWithEmailAndPassword(Activity activity, String email, String password, boolean remember){
        boolean lastRemember = sharedPreferences.getBoolean("remember", false);
        if (lastRemember){
            String savedEmail = sharedPreferences.getString("email", "");
            String savedPassword = sharedPreferences.getString("password","");
            firebaseAuth.signInWithEmailAndPassword(savedEmail, savedPassword).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    sharedPreferences.edit().putBoolean("remember", remember);
                    sharedPreferences.edit().commit();
                    loginSuccessfully.postValue(true);
                } else {
                    loginSuccessfully.postValue(false);
                }
            });
        } else {
            if (email.isEmpty() || password.isEmpty()){
                loginSuccessfully.postValue(false);
            } else {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()){
                        if (remember){
                            sharedPreferences.edit().putBoolean("remember", true);
                            sharedPreferences.edit().putString("email", email);
                            sharedPreferences.edit().putString("password", password);
                            sharedPreferences.edit().commit();
                        }
                        loginSuccessfully.postValue(true);
                    } else {
                        loginSuccessfully.postValue(false);
                    }
                });
            }
        }

    }

    public void logOutUser(){
        firebaseAuth.signOut();
        loginSuccessfully.postValue(false);
    }

    public void getCurrentUserData(){
        FirebaseUser currentUser1 = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentUser1.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue().toString();
                String email = snapshot.child("email").getValue().toString();
                String phoneNumber = snapshot.child("phone_number").getValue().toString();
                String avatar = snapshot.child("avatar").getValue().toString();
                String dateOfBirth = snapshot.child("date_of_birth").getValue().toString();
                String sex = snapshot.child("sex").getValue().toString();
                user = new User(name, email, phoneNumber, avatar, sex, dateOfBirth);
                currentUser.postValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void changeCurrentUserData(String name, String phoneNumber, String dateOfBirth, String sex, Uri uri){
        FirebaseUser currentUser1 = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentUser1.getUid();
        HashMap<String, String> userInfo = new HashMap<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        String currentAvatar = user.getUserAvatar();
        userInfo.put("avatar", currentAvatar);

        if (uri != null){
            storageReference = FirebaseStorage.getInstance().getReference();
            StorageReference filePath = storageReference.child("user_avatars").child(uid + ".jpg");
            filePath.putFile(uri).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    String newAvatar = filePath.getDownloadUrl().toString();
                    userInfo.put("avatar", newAvatar);
                } else {

                }
            });
        } else {

        }

        userInfo.put("name", name);
        userInfo.put("phone_number", phoneNumber);
        userInfo.put("sex", sex);
        userInfo.put("date_of_birth", dateOfBirth);
        userInfo.put("email", currentUser1.getEmail());
        databaseReference.setValue(userInfo).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                getCurrentUserData();
            }
        });

    }
}
