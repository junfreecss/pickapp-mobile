package com.pickapp.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.pickapp.data.remote.model.Data;
import com.pickapp.data.remote.model.User;
import com.pickapp.interactor.LoginUseCase;
import com.pickapp.interactor.RegisterUseCase;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class AuthViewModel extends ViewModel {

    private RegisterUseCase registerUseCase;
    private LoginUseCase loginUseCase;
    private User user;

    public AuthViewModel(RegisterUseCase registerUseCase, LoginUseCase loginUseCase) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        user = new User();
    }

    public void register(User user) {

        registerUseCase.execute(new DisposableObserver<Data>() {
            @Override
            public void onNext(@NonNull Data data) {
                Log.d("ddd", data.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("ddd", e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("ddd", "onComplete");
            }
        }, user);
    }

    public void login(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        loginUseCase.execute(new DisposableObserver<Data>() {
            @Override
            public void onNext(@NonNull Data data) {
                Log.d("ddd", data.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("ddd", e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("ddd", "onComplete >>>");
            }
        }, user);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        registerUseCase.dispose();
        loginUseCase.dispose();
    }
}
