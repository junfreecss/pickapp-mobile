package com.pickapp.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.pickapp.R;
import com.pickapp.app.PickApp;
import com.pickapp.databinding.FragmentAuthResetPasswordBinding;
import com.pickapp.ui.viewmodel.AuthVMFactory;

import javax.inject.Inject;

public class ResetPasswordFragment extends Fragment {

    private FragmentAuthResetPasswordBinding binding;

    @Inject
    AuthVMFactory vmFactory;
    private AuthViewModel authVM;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        PickApp.getAppComponent().inject(ResetPasswordFragment.this);
        authVM = new ViewModelProvider(getActivity(), vmFactory).get(AuthViewModel.class);

        binding = FragmentAuthResetPasswordBinding.inflate(inflater, container, false);
        binding.toolbar.title.setText("Reset Password");

        binding.toolbar.btnBack.setOnClickListener(v -> getActivity().onBackPressed());

        binding.btnRegister.setOnClickListener(v -> {

        });


        return binding.getRoot();
    }

}
