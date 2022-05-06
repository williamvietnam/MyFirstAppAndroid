package com.williamnb.readlistenapp.features.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.firestore.FirebaseFirestore;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentSignUpBinding;
import com.williamnb.readlistenapp.preferences.PreferenceManager;
import com.williamnb.readlistenapp.utilities.Constants;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class SignUpFragment extends BaseFragment<FragmentSignUpBinding, SignUpViewModel> {
    private static final int RESULT_OK = -1;
    private String encodeImage;
    private PreferenceManager preferenceManager;

    @Override
    public FragmentSignUpBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentSignUpBinding.inflate(inflater, container, false);
    }

    @Override
    public SignUpViewModel createViewModel() {
        return new ViewModelProvider(this).get(SignUpViewModel.class);
    }

    @Override
    public void initializeView() {
        hideBottomNavigationView(true);
    }

    @Override
    public void initializeComponent() {

    }

    @Override
    public void initializeEvents() {
        viewBinding.btnSignIn.setOnClickListener(view -> findNavController().popBackStack());
        viewBinding.btnBack.setOnClickListener(view -> findNavController().popBackStack());
        viewBinding.btnSignUp.setOnClickListener(view -> {
            if (isValidSignUpDetails()) {
                signUp();
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
        preferenceManager = new PreferenceManager(requireContext());
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void signUp() {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String, Object> user = new HashMap<>();
        user.put(Constants.KEY_NAME, viewBinding.inputName.getText().toString());
        user.put(Constants.KEY_EMAIL, viewBinding.inputAccount.getText().toString());
        user.put(Constants.KEY_PASSWORD, viewBinding.inputPassword.getText().toString());
        user.put(Constants.KEY_IMAGE, encodeImage);
        database.collection(Constants.KEY_COLLECTION_USERS)
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    loading(false);
                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                    preferenceManager.putString(Constants.KEY_USER_ID, documentReference.getId());
                    preferenceManager.putString(Constants.KEY_NAME, viewBinding.inputName.getText().toString());
                    preferenceManager.putString(Constants.KEY_IMAGE, encodeImage);
                    findNavController().popBackStack();
//                    Intent intent = new Intent(getActivity(), ChatFragment.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
                })
                .addOnFailureListener(exception -> {
                    loading(false);
                    showToast(exception.getMessage());
                });
    }

    private String encodeImage(Bitmap bitmap) {
        int previewWidth = 150;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            InputStream inputStream = requireContext().getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            viewBinding.imvAvatar.setImageBitmap(bitmap);
                            viewBinding.tvAddImage.setVisibility(View.GONE);
                            encodeImage = encodeImage(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    private Boolean isValidSignUpDetails() {
        if (encodeImage == null) {
            showToast("Thêm ảnh hồ sơ");
            return false;
        } else if (viewBinding.inputName.getText().toString().trim().isEmpty()) {
            showToast("Mời nhập Họ Tên");
            return false;
        } else if (viewBinding.inputAccount.getText().toString().trim().isEmpty()) {
            showToast("Mời nhập Tài khoản");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(viewBinding.inputAccount.getText().toString()).matches()) {
            showToast("Nhập đúng định dạng Email");
            return false;
        } else if (viewBinding.inputPassword.getText().toString().trim().isEmpty()) {
            showToast("Nhập mật khẩu");
            return false;
        } else if (viewBinding.inputConfirmPassword.getText().toString().trim().isEmpty()) {
            showToast("Nhập lại mật khẩu");
            return false;
        } else if (!viewBinding.inputPassword.getText().toString().equals(viewBinding.inputConfirmPassword.getText().toString())) {
            showToast("Mật khẩu và nhập lại mật khẩu phải trùng nhau");
            return false;
        } else {
            return true;
        }
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            viewBinding.btnSignUp.setVisibility(View.INVISIBLE);
            viewBinding.progressBar.setVisibility(View.VISIBLE);
        } else {
            viewBinding.progressBar.setVisibility(View.INVISIBLE);
            viewBinding.btnSignUp.setVisibility(View.VISIBLE);
        }
    }
}
