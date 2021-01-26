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

    private boolean isShowingCodeEditText = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        PickApp.getAppComponent().inject(ResetPasswordFragment.this);
        authVM = new ViewModelProvider(getActivity(), vmFactory).get(AuthViewModel.class);

        binding = FragmentAuthResetPasswordBinding.inflate(inflater, container, false);
        binding.toolbar.title.setText("Reset Password");

        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowingCodeEditText) {
                    toggleCodeEditText(false);
                } else {
                    getActivity().onBackPressed();
                }
            }
        });

        binding.btnNext.setOnClickListener(v -> {
            if (isShowingCodeEditText) {
                Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                    .navigate(R.id.auth_change_password);
            } else {
                toggleCodeEditText(true);
            }
        });

        return binding.getRoot();
    }

    private void toggleCodeEditText(boolean showCodeEditText) {
        binding.emailLayout.setVisibility(showCodeEditText ? View.GONE : View.VISIBLE);
        binding.code.setVisibility(showCodeEditText ? View.VISIBLE : View.GONE);
        binding.toolbar.title.setText(showCodeEditText ? "Enter Code" : "Reset Password");

        if (!showCodeEditText) {
            binding.code.setText("");
        }

        isShowingCodeEditText = showCodeEditText;
    }

}
