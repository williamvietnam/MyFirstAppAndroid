package com.williamnb.readlistenapp.ui.features.login;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseActivity;
import com.williamnb.readlistenapp.databinding.ActivitySignInBinding;
import com.williamnb.readlistenapp.ui.features.main.MainActivity;

public class SignInActivity extends BaseActivity<ActivitySignInBinding, SignInViewModel> {

    @Override
    protected ActivitySignInBinding getActivityBinding() {
        return ActivitySignInBinding.inflate(getLayoutInflater());
    }

    @Override
    public SignInViewModel createViewModel() {
        return new ViewModelProvider(this).get(SignInViewModel.class);
    }

    @Override
    public void initializeView() {
    }

    @Override
    public void initializeComponent() {
    }

    @Override
    public void initializeEvents() {
        viewBinding.btnSignIn.setOnClickListener(view -> {
            if (isValidSignInDetails()) {
                viewModel.signIn(
                        viewBinding.inputAccount.getText().toString(),
                        viewBinding.inputPassword.getText().toString()
                );
            }
        });
    }

    @Override
    public void initializeData() {
        final Observer<Boolean> checkLoading = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                loading(isLoading);
            }
        };
        viewModel.getIsLoading().observe(this, checkLoading);

        final Observer<Boolean> checkOpenMainScreen = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isOpenMainScreen) {
                if (isOpenMainScreen) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        viewModel.getOpenMainScreen().observe(this, checkOpenMainScreen);
    }

    private void loading(@NonNull Boolean isLoading) {
        if (isLoading) {
            viewBinding.btnSignIn.setVisibility(View.INVISIBLE);
            viewBinding.progressBar.setVisibility(View.VISIBLE);
        } else {
            viewBinding.btnSignIn.setVisibility(View.VISIBLE);
            viewBinding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @NonNull
    private Boolean isValidSignInDetails() {
        if (viewBinding.inputAccount.getText().toString().trim().isEmpty()) {
            viewModel.showToast("Nhập Email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(viewBinding.inputAccount.getText().toString()).matches()) {
            viewModel.showToast("Nhập đúng định dạng email");
            return false;
        } else if (viewBinding.inputPassword.getText().toString().trim().isEmpty()) {
            viewModel.showToast("Nhập mật khẩu");
            return false;
        } else {
            return true;
        }
    }
}