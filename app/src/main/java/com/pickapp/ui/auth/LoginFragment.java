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
import com.pickapp.databinding.FragmentAuthLoginBinding;
import com.pickapp.ui.viewmodel.AuthVMFactory;

import javax.inject.Inject;

public class LoginFragment extends Fragment {

    private FragmentAuthLoginBinding binding;

    @Inject
    AuthVMFactory vmFactory;
    private AuthViewModel authVM;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        PickApp.getAppComponent().inject(LoginFragment.this);
        authVM = new ViewModelProvider(getActivity(), vmFactory).get(AuthViewModel.class);

        binding = FragmentAuthLoginBinding.inflate(inflater, container, false);
        binding.toolbar.title.setText("Login");

        binding.toolbar.btnBack.setOnClickListener(v -> getActivity().onBackPressed());

        binding.btnRegister.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                    .navigate(R.id.auth_register);
        });

        binding.resetPassword.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                    .navigate(R.id.auth_reset_password);
        });

        return binding.getRoot();
    }
}
