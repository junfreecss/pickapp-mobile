package com.pickapp.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.pickapp.app.PickApp;
import com.pickapp.databinding.ActivityAuthBinding;
import com.pickapp.ui.viewmodel.AuthVMFactory;

import javax.inject.Inject;

public class AuthActivity extends AppCompatActivity {

    private ActivityAuthBinding binding;

    @Inject
    AuthVMFactory vmFactory;
    private AuthViewModel authVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PickApp.getAppComponent().inject(this);

        authVM = new ViewModelProvider(AuthActivity.this, vmFactory).get(AuthViewModel.class);

        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}