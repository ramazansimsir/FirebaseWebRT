package com.example.firebasewebrt.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasewebrt.R;
import com.example.firebasewebrt.databinding.ActivityLoginBinding;
import com.example.firebasewebrt.repository.MainRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.permissionx.guolindev.PermissionX;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private MainRepository mainRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


       init();

    }


    private void init() {
        mainRepository = MainRepository.getInstance();
        binding.enterBtn.setOnClickListener(v -> {
            PermissionX.init(this)
                    .permissions(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO)
                    .request((allGranted, grantedList, deniedList) -> {
                        if (allGranted) {
                            //login to firebase here

                            mainRepository.login(
                                    binding.username.getText().toString(), getApplicationContext(), () -> {
                                        //if success then we want to move to call activity
                                        startActivity(new Intent(LoginActivity.this, CallActivity.class));
                                    }
                            );
                        }
                    });


        });
    }
}