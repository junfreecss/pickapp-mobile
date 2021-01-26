package com.pickapp.ui.auth;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.pickapp.MainActivity;
import com.pickapp.R;
import com.pickapp.app.Message;
import com.pickapp.app.PickApp;
import com.pickapp.data.remote.model.Data;
import com.pickapp.databinding.FragmentAuthLoginBinding;
import com.pickapp.ui.util.Helper;
import com.pickapp.ui.util.ResultObserver;
import com.pickapp.ui.viewmodel.AuthVMFactory;
import com.pickapp.util.Logger;

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
        authVM = new ViewModelProvider(this, vmFactory).get(AuthViewModel.class);

        binding = FragmentAuthLoginBinding.inflate(inflater, container, false);
        binding.toolbar.title.setText("Login");
        binding.toolbar.btnBack.setOnClickListener(v -> getActivity().onBackPressed());

        initForm();
        setVmObserver();

        return binding.getRoot();
    }

    private void setVmObserver() {
        authVM.getResponseData().observe(getViewLifecycleOwner(), result -> new ResultObserver(result) {
            @Override
            public void onSuccess(Data data) {
                 startActivity(new Intent(getActivity(), MainActivity.class));
            }

            @Override
            public void onError(Throwable throwable) {
                Logger.E("Login error", throwable);

                String errMsg = "";
                if (throwable.getMessage().contains("Unable to resolve host")) {
                    errMsg = Message.LOGIN_INTERNET_ERROR;
                } else if (throwable.getMessage().contains("401 Unauthorized")) {
                    errMsg = "Incorrect email or password. Try again.";
                } else {
                    errMsg = Message.SOMETHING_WENT_WRONG;
                }

                Helper.dialogAlert(getContext(), "Login Failed", errMsg);
                enabledLoginForm(true);
            }
        });
    }

    private void enabledLoginForm(boolean enabled) {
        Helper.enableView(binding.parent, enabled);
        binding.btnLogin.setEnabled(enabled);
        binding.progress.setVisibility(enabled ? View.GONE : View.VISIBLE);
    }

    private void initForm() {
        Helper.addTextWatcher(binding.email, binding.emailLayout);
        Helper.addTextWatcher(binding.password, binding.passwordLayout);

        binding.btnLogin.setOnClickListener(v -> {
            if (TextUtils.isEmpty(binding.email.getText()) || !Patterns.EMAIL_ADDRESS.matcher(binding.email.getText()).matches()) {
                binding.emailLayout.setError("Invalid email address.");
            } else {
                enabledLoginForm(false);
                authVM.login(binding.email.getText().toString(), binding.password.getText().toString());
            }
        });

        binding.btnRegister.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                    .navigate(R.id.auth_register);
        });

        binding.resetPassword.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                    .navigate(R.id.auth_reset_password);
        });
    }
}
