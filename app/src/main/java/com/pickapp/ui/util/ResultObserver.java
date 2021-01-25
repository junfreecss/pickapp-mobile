package com.pickapp.ui.util;

import com.pickapp.data.remote.model.Data;
import com.pickapp.ui.Result;

public abstract class ResultObserver {

    private Result result;

    public ResultObserver(Result result) {
        this.result = result;

        if (result instanceof Result.Success) {
            onSuccess(((Result.Success<Data>) result).getData());
        } else {
            onError(((Result.Error) result).getError());
        }
    }

    public abstract void onSuccess(Data data);

    public abstract void onError(Throwable throwable);
}
