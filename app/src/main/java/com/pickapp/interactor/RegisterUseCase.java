package com.pickapp.interactor;

import com.pickapp.data.remote.model.Data;
import com.pickapp.data.remote.model.User;
import com.pickapp.data.repository.AuthRepository;
import com.pickapp.interactor.base.UseCase;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class RegisterUseCase extends UseCase<Data, User> {

    @Inject
    AuthRepository authRepository;

    @Inject
    public RegisterUseCase() { }

    @Override
    protected Observable<Data> buildUseCaseObservable(User user) {
        return authRepository.register(user);
    }
}
