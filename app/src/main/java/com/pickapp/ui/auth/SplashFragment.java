package com.pickapp.ui.auth;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.pickapp.R;
import com.pickapp.databinding.FragmentAuthSplashBinding;

public class SplashFragment extends Fragment {

    private FragmentAuthSplashBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAuthSplashBinding.inflate(inflater, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                        .navigate(R.id.action_auth_splash_to_auth_get_started);
            }
        }, 1000);

        return binding.getRoot();
    }
}
