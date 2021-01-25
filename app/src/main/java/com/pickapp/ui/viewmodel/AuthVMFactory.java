package com.pickapp.ui.viewmodel;

import com.pickapp.interactor.LoginUseCase;
import com.pickapp.interactor.RegisterUseCase;
import com.pickapp.ui.auth.AuthViewModel;

import javax.inject.Inject;

public class AuthVMFactory extends VMFactory<AuthViewModel> {

    @Inject
    RegisterUseCase registerUseCase;

    @Inject
    LoginUseCase loginUseCase;

    @Inject
    public AuthVMFactory() { }

    @Override
    protected Class getVmClass() {
        return AuthViewModel.class;
    }

    @Override
    protected AuthViewModel resolveVM() {
        return new AuthViewModel(registerUseCase, loginUseCase);
    }
}
