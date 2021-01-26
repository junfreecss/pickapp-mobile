package com.pickapp.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pickapp.data.remote.model.Data;
import com.pickapp.data.remote.model.User;
import com.pickapp.data.repository.AuthRepository;
import com.pickapp.interactor.LoginUseCase;
import com.pickapp.interactor.RegisterUseCase;
import com.pickapp.ui.Result;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class AuthViewModel extends ViewModel {

    private AuthRepository authRepository;

    private RegisterUseCase registerUseCase;
    private LoginUseCase loginUseCase;

    private MutableLiveData<Result> responseData = new MutableLiveData<>();

    public AuthViewModel(AuthRepository authRepository, RegisterUseCase registerUseCase, LoginUseCase loginUseCase) {
        this.authRepository = authRepository;
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
    }

    public LiveData<Result> getResponseData() {
        return responseData;
    }

    public void register(User user) {

        registerUseCase.execute(new DisposableObserver<Data>() {
            @Override
            public void onNext(@NonNull Data data) {
                responseData.setValue(new Result.Success<>(data));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                responseData.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() {}
        }, user);
    }

    public void login(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        loginUseCase.execute(new DisposableObserver<Data>() {
            @Override
            public void onNext(@NonNull Data data) {
                responseData.setValue(new Result.Success<>(data));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                responseData.setValue(new Result.Error(e));
            }

            @Override
            public void onComplete() { }
        }, user);
    }

    public User getUser() {
        return authRepository.getUser();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        registerUseCase.dispose();
        loginUseCase.dispose();
    }
}
