package com.williamnb.readlistenapp.ui.features.login;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.utilities.Constants;

public class SignInViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<Boolean> openMainScreen;

    public SignInViewModel(@NonNull Application application) {
        super(application);
        isLoading = new MutableLiveData<>();
        openMainScreen = new MutableLiveData<>();
        this.openMainScreen.setValue(false);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        if (isLoading == null) {
            isLoading = new MutableLiveData<Boolean>();
        }
        return isLoading;
    }

    public MutableLiveData<Boolean> getOpenMainScreen() {
        if (openMainScreen == null) {
            openMainScreen = new MutableLiveData<Boolean>();
        }
        return openMainScreen;
    }

    public void showToast(String message) {
        Toast.makeText(getViewModelContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void signIn(String account, String password) {
        this.isLoading.setValue(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .whereEqualTo(Constants.KEY_EMAIL, account)
                .whereEqualTo(Constants.KEY_PASSWORD, password)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null
                            && task.getResult().getDocuments().size() > 0) {
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                        getPreferenceManager().putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                        getPreferenceManager().putString(Constants.KEY_USER_ID, documentSnapshot.getId());
                        getPreferenceManager().putString(Constants.KEY_NAME, documentSnapshot.getString(Constants.KEY_NAME));
                        getPreferenceManager().putString(Constants.KEY_IMAGE, documentSnapshot.getString(Constants.KEY_IMAGE));
                        this.openMainScreen.setValue(true);
                    } else {
                        this.isLoading.setValue(false);
                        showToast("Không thể đăng nhập");
                    }
                });
    }
}