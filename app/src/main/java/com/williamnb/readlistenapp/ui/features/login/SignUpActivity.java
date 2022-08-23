package com.williamnb.readlistenapp.ui.features.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseActivity;
import com.williamnb.readlistenapp.databinding.ActivitySignUpBinding;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding, SignUpViewModel> {

    private static final int RESULT_OK = -1;
    private String encodeImage;
    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            InputStream inputStream = getApplicationContext().getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            viewBinding.imvAvatar.setImageBitmap(bitmap);
                            viewBinding.tvAddImage.setVisibility(View.GONE);
                            this.encodeImage = viewModel.encodeImage(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    @Override
    public SignUpViewModel createViewModel() {
        return new ViewModelProvider(this).get(SignUpViewModel.class);
    }

    @Override
    public ActivitySignUpBinding getActivityBinding() {
        return ActivitySignUpBinding.inflate(getLayoutInflater());
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
            Log.d(SignUpActivity.class.getSimpleName(), "debug: comeback sign in");
            onBackPressed();
        });

        viewBinding.btnBack.setOnClickListener(view -> {
            Log.d(SignUpActivity.class.getSimpleName(), "debug: comeback sign in");
            onBackPressed();
        });

        viewBinding.btnSignUp.setOnClickListener(view -> {
            if (isValidSignUpDetails()) {
                viewModel.signUp(
                        viewBinding.inputName.getText().toString(),
                        viewBinding.inputAccount.getText().toString(),
                        viewBinding.inputPassword.getText().toString(),
                        this.encodeImage
                );
            }
        });

        viewBinding.layoutImage.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });
    }

    @Override
    public void initializeData() {
        final Observer<Boolean> loadingObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                loading(isLoading);
            }
        };
        viewModel.getIsLoading().observe(this, loadingObserver);

        final Observer<Boolean> openSignInScreenObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@NonNull Boolean isOpenSignInScreen) {
                if (isOpenSignInScreen) {
                    onBackPressed();
                }
            }
        };
        viewModel.getOpenSignInScreen().observe(this, openSignInScreenObserver);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent startActivityIntent = new Intent(this, SignInActivity.class);
        startActivity(startActivityIntent);
        finish();
    }

    @NonNull
    private Boolean isValidSignUpDetails() {
        if (encodeImage == null) {
            viewModel.showToast("Thêm ảnh hồ sơ");
            return false;
        } else if (viewBinding.inputName.getText().toString().trim().isEmpty()) {
            viewModel.showToast("Mời nhập Họ Tên");
            return false;
        } else if (viewBinding.inputAccount.getText().toString().trim().isEmpty()) {
            viewModel.showToast("Mời nhập Tài khoản");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(viewBinding.inputAccount.getText().toString()).matches()) {
            viewModel.showToast("Nhập đúng định dạng Email");
            return false;
        } else if (viewBinding.inputPassword.getText().toString().trim().isEmpty()) {
            viewModel.showToast("Nhập mật khẩu");
            return false;
        } else if (viewBinding.inputConfirmPassword.getText().toString().trim().isEmpty()) {
            viewModel.showToast("Nhập lại mật khẩu");
            return false;
        } else if (!viewBinding.inputPassword.getText().toString().equals(viewBinding.inputConfirmPassword.getText().toString())) {
            viewModel.showToast("Mật khẩu và nhập lại mật khẩu phải trùng nhau");
            return false;
        } else {
            return true;
        }
    }

    private void loading(@NonNull Boolean isLoading) {
        if (isLoading) {
            viewBinding.btnSignUp.setVisibility(View.INVISIBLE);
            viewBinding.progressBar.setVisibility(View.VISIBLE);
        } else {
            viewBinding.progressBar.setVisibility(View.INVISIBLE);
            viewBinding.btnSignUp.setVisibility(View.VISIBLE);
        }
    }
}