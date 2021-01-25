package com.pickapp.ui.auth;

import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pickapp.app.PickApp;
import com.pickapp.data.remote.model.Data;
import com.pickapp.data.remote.model.User;
import com.pickapp.databinding.FragmentAuthRegisterBinding;
import com.pickapp.ui.util.Helper;
import com.pickapp.ui.util.ResultObserver;
import com.pickapp.ui.viewmodel.AuthVMFactory;
import com.pickapp.util.Logger;

import javax.inject.Inject;

public class RegisterFragment extends Fragment {

    private FragmentAuthRegisterBinding binding;

    @Inject
    AuthVMFactory authVmFactory;
    private AuthViewModel authVM;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        PickApp.getAppComponent().inject(RegisterFragment.this);
        authVM = new ViewModelProvider(getActivity(), authVmFactory).get(AuthViewModel.class);

        binding = FragmentAuthRegisterBinding.inflate(inflater, container, false);
        binding.toolbar.title.setText("Register");

        initForm();
        setVmObserver();

        return binding.getRoot();
    }

    private void setVmObserver() {
        authVM.getResponseData().observe(getViewLifecycleOwner(), result -> {
            new ResultObserver(result) {
                @Override
                public void onSuccess(Data data) {
                    Logger.V(data);
                }

                @Override
                public void onError(Throwable throwable) {
                    Logger.V(throwable);
                }
            };

            clearForm();
            enabledRegistrationForm(true);
        });
    }

    private void initForm() {
        addTextWatcher(binding.firstName, binding.firstNameLayout);
        addTextWatcher(binding.lastName, binding.lastNameLayout);
        addTextWatcher(binding.phone, binding.phoneLayout);
        addTextWatcher(binding.email, binding.emailLayout);
        addTextWatcher(binding.password, binding.passwordLayout);
        addTextWatcher(binding.confirmPassword, binding.confirmPasswordLayout);

        binding.btnRegister.setOnClickListener(v -> {
            if (validateForm()) {
                User user = new User();
                user.setFirstName(binding.firstName.getText().toString());
                user.setLastName(binding.firstName.getText().toString());
                user.setPhone(binding.phone.getText().toString());
                user.setEmail(binding.email.getText().toString());
                user.setPassword(binding.password.getText().toString());
                enabledRegistrationForm(false);
                authVM.register(user);
            }
        });

        binding.toolbar.btnBack.setOnClickListener(v -> getActivity().onBackPressed());
    }

    private void enabledRegistrationForm(boolean enabled) {
        Helper.enableView(binding.parent, enabled);
        binding.btnRegister.setEnabled(enabled);
        binding.progress.setVisibility(enabled ? View.GONE : View.VISIBLE);
    }

    private void addTextWatcher(TextInputEditText editText, TextInputLayout editTextLayout) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editTextLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void clearForm() {
        binding.firstName.getText().clear();
        binding.lastName.getText().clear();
        binding.phone.getText().clear();
        binding.email.getText().clear();
        binding.password.getText().clear();
        binding.confirmPassword.getText().clear();
    }

    private boolean validateForm() {
        boolean noError = true;

        if (TextUtils.isEmpty(binding.firstName.getText())) {
            binding.firstNameLayout.setError("Invalid first name");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.lastName.getText())) {
            binding.lastNameLayout.setError("Invalid last name.");
            noError = false;
        }

        if (!PhoneNumberUtils.isGlobalPhoneNumber(binding.phone.getText().toString()) || binding.phone.getText().toString().trim().equals("")) {
            binding.phoneLayout.setError("Invalid phone number.");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.email.getText()) || !Patterns.EMAIL_ADDRESS.matcher(binding.email.getText()).matches()) {
            binding.emailLayout.setError("Invalid email address.");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.password.getText()) || binding.password.length() < 6) {
            binding.passwordLayout.setError("Password must be at least 6 characters.");
            noError = false;
        }

        if (TextUtils.isEmpty(binding.confirmPassword.getText()) || !binding.password.getText().toString().equals(binding.confirmPassword.getText().toString())) {
            binding.confirmPasswordLayout.setError("Please confirm password.");
            noError = false;
        }

        return noError;
    }
}
