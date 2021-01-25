package com.pickapp.interactor.base;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class UseCase<T, Params>  {

    private CompositeDisposable disposables;

    protected UseCase() {
        disposables = new CompositeDisposable();
    }

    protected abstract Observable<T> buildUseCaseObservable(Params params);

    protected void observe(Observable<T> observable){}

    public void execute(DisposableObserver<T> observer, Params params) {
        Observable<T> observable = buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposables.add(observable.subscribeWith(observer));

        observe(observable);
    }

    public void longPolling(Observable<T> observer, Params params) {

    }

    public void dispose() {
        if (! disposables.isDisposed()) {
            disposables.dispose();
        }
    }

}