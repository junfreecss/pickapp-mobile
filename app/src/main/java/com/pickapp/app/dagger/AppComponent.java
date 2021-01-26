package com.pickapp.app.dagger;

import com.pickapp.databinding.FragmentAuthChangePasswordBinding;
import com.pickapp.ui.auth.AuthActivity;
import com.pickapp.ui.auth.ChangePasswordFragment;
import com.pickapp.ui.auth.LoginFragment;
import com.pickapp.ui.auth.RegisterFragment;
import com.pickapp.ui.auth.ResetPasswordFragment;

import dagger.Component;

@Component(modules = {ContextModule.class})
public interface AppComponent {

    void inject(AuthActivity authActivity);

    void inject(RegisterFragment registerFragment);
    void inject(LoginFragment loginFragment);
    void inject(ResetPasswordFragment resetPasswordFragment);
    void inject(ChangePasswordFragment changePasswordFragment);

}
