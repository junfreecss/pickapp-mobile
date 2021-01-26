package com.pickapp.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pickapp.app.PickApp;
import com.pickapp.databinding.FragmentAuthChangePasswordBinding;
import com.pickapp.databinding.FragmentAuthResetPasswordBinding;
import com.pickapp.ui.viewmodel.AuthVMFactory;

import javax.inject.Inject;

public class ChangePasswordFragment extends Fragment {

    private FragmentAuthChangePasswordBinding binding;

    @Inject
    AuthVMFactory vmFactory;
    private AuthViewModel authVM;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        PickApp.getAppComponent().inject(ChangePasswordFragment.this);
        authVM = new ViewModelProvider(getActivity(), vmFactory).get(AuthViewModel.class);

        binding = FragmentAuthChangePasswordBinding.inflate(inflater, container, false);
        binding.toolbar.title.setText("Change Password");

        binding.toolbar.btnBack.setOnClickListener(v -> getActivity().onBackPressed());

        binding.btnRegister.setOnClickListener(v -> {

        });


        return binding.getRoot();
    }

}
