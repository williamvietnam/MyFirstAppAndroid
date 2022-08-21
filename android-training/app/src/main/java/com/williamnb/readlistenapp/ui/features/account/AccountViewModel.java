package com.williamnb.readlistenapp.ui.features.account;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.utilities.Constants;

import java.util.HashMap;

public class AccountViewModel extends BaseViewModel {
    private MutableLiveData<Boolean> logoutLiveData;

    public AccountViewModel(@NonNull Application application) {
        super(application);
        if (logoutLiveData == null) {
            this.logoutLiveData = new MutableLiveData<>();
        }
    }

    public MutableLiveData<Boolean> getLogoutLiveData() {
        return logoutLiveData;
    }

    public void logout() {
        Toast.makeText(getViewModelContext(), "Đang đăng xuất...", Toast.LENGTH_SHORT).show();
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        getPreferenceManager().getString(Constants.KEY_USER_ID)
                );
        HashMap<String, Object> updates = new HashMap<>();
        updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    getPreferenceManager().putBoolean(Constants.KEY_IS_SIGNED_IN, false);
//                    getPreferenceManager().clear();
                    this.logoutLiveData.setValue(true);
                    Log.d(AccountFragment.class.getName(), "debug: logout SUCCESS");
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getViewModelContext(), "Không thể đăng xuất", Toast.LENGTH_SHORT).show();
                    Log.d(AccountFragment.class.getName(), "debug: cannot logout");
                });
    }
}