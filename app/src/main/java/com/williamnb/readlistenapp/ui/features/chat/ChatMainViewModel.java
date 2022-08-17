package com.williamnb.readlistenapp.ui.features.chat;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.utilities.Constants;

import java.util.HashMap;

public class ChatMainViewModel extends BaseViewModel {

    private final MutableLiveData<Boolean> isSignedOut;
    private final MutableLiveData<String> userName;
    private final MutableLiveData<String> imageAvatar;

    public ChatMainViewModel(@NonNull Application application) {
        super(application);
        this.isSignedOut = new MutableLiveData<>();
        this.userName = new MutableLiveData<>();
        this.imageAvatar = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getIsSignedOut() {
        return this.isSignedOut;
    }

    public MutableLiveData<String> getUserName() {
        return userName;
    }

    public MutableLiveData<String> getImageAvatar() {
        return imageAvatar;
    }

    public void showToast(String message) {
        Toast.makeText(getViewModelContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void getToken() {
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateToken);
    }

    public void updateToken(String token) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        getPreferenceManager().getString(Constants.KEY_USER_ID)
                );
        documentReference.update(Constants.KEY_FCM_TOKEN, token)
//                .addOnSuccessListener(unused -> showToast("Token đã cập nhật thành công"))
                .addOnFailureListener(e -> showToast("Không thể cập nhật token"));
    }

    public void signOut() {
        showToast("Đang đăng xuất...");
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        getPreferenceManager().getString(Constants.KEY_USER_ID)
                );
        HashMap<String, Object> updates = new HashMap<>();
        updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    getPreferenceManager().clear();
                    this.isSignedOut.setValue(true);
                })
                .addOnFailureListener(e -> showToast("Không thể đăng xuất"));
    }

    public void getUserInformation() {
        this.userName.setValue(getPreferenceManager().getString(Constants.KEY_NAME));
        this.imageAvatar.setValue(getPreferenceManager().getString(Constants.KEY_IMAGE));
    }
}