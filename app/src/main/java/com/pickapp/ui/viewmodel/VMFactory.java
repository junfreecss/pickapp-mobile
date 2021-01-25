package com.pickapp.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public abstract class VMFactory<VM extends ViewModel> implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(getVmClass())) {
            return (T) resolveVM();
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

    protected abstract Class getVmClass();

    protected abstract VM resolveVM();

}
