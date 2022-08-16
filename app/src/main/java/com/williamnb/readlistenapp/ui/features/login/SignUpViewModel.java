package com.williamnb.readlistenapp.ui.features.login;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.FirebaseFirestore;
import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.utilities.Constants;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class SignUpViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<Boolean> openSignInScreen;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        isLoading = new MutableLiveData<>();
        openSignInScreen = new MutableLiveData<>();
        this.openSignInScreen.setValue(false);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        if (isLoading == null) {
            isLoading = new MutableLiveData<Boolean>();
        }
        return isLoading;
    }

    public MutableLiveData<Boolean> getOpenSignInScreen() {
        if (openSignInScreen == null) {
            openSignInScreen = new MutableLiveData<Boolean>();
        }
        return openSignInScreen;
    }

    public void signUp(String inputName, String inputAccount, String inputPassword, String encodeImage) {
        isLoading.setValue(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String, Object> user = new HashMap<>();
        user.put(Constants.KEY_NAME, inputName);
        user.put(Constants.KEY_EMAIL, inputAccount);
        user.put(Constants.KEY_PASSWORD, inputPassword);
        user.put(Constants.KEY_IMAGE, encodeImage);
        database.collection(Constants.KEY_COLLECTION_USERS)
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    isLoading.setValue(false);
                    getPreferenceManager().putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                    getPreferenceManager().putString(Constants.KEY_USER_ID, documentReference.getId());
                    getPreferenceManager().putString(Constants.KEY_NAME, inputName);
                    getPreferenceManager().putString(Constants.KEY_IMAGE, encodeImage);
                    openSignInScreen.setValue(true);
                })
                .addOnFailureListener(exception -> {
                    isLoading.setValue(false);
                    showToast(exception.getMessage());
                });
    }

    public void showToast(String message) {
        Toast.makeText(getViewModelContext(), message, Toast.LENGTH_SHORT).show();
    }

    public String encodeImage(@NonNull Bitmap bitmap) {
        int previewWidth = 150;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}