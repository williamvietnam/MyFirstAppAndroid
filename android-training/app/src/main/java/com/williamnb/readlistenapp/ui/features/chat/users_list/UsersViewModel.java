package com.williamnb.readlistenapp.ui.features.chat.users_list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.models.User;
import com.williamnb.readlistenapp.utilities.Constants;

import java.util.ArrayList;
import java.util.List;

public class UsersViewModel extends BaseViewModel {

    private final List<User> userList;
    private final MutableLiveData<List<User>> bindUserAdapter;
    private final MutableLiveData<Boolean> isShowErrorMessage;
    private final MutableLiveData<Boolean> isShowLoading;

    public UsersViewModel(@NonNull Application application) {
        super(application);
        userList = new ArrayList<>();
        this.bindUserAdapter = new MutableLiveData<>();
        this.isShowErrorMessage = new MutableLiveData<>();
        this.isShowLoading = new MutableLiveData<>();
    }

    public MutableLiveData<List<User>> getBindUserAdapter() {
        return bindUserAdapter;
    }

    public MutableLiveData<Boolean> getIsShowErrorMessage() {
        return this.isShowErrorMessage;
    }

    public MutableLiveData<Boolean> getIsShowLoading() {
        return this.isShowLoading;
    }

    public void getUsers() {
        isShowLoading.setValue(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .get()
                .addOnCompleteListener(task -> {
                    isShowLoading.setValue(false);
                    String currentUserId = getPreferenceManager().getString(Constants.KEY_USER_ID);
                    if (task.isSuccessful() && task.getResult() != null) {
                        this.userList.clear();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            if (currentUserId.equals(queryDocumentSnapshot.getId())) {
                                continue;
                            }
                            User user = new User();
                            user.setName(queryDocumentSnapshot.getString(Constants.KEY_NAME));
                            user.setEmail(queryDocumentSnapshot.getString(Constants.KEY_EMAIL));
                            user.setImage(queryDocumentSnapshot.getString(Constants.KEY_IMAGE));
                            user.setToken(queryDocumentSnapshot.getString(Constants.KEY_FCM_TOKEN));
                            user.setId(queryDocumentSnapshot.getId());
                            this.userList.add(user);
                        }
                        if (this.userList.size() > 0) {
                            this.bindUserAdapter.setValue(this.userList);
                        } else {
                            isShowErrorMessage.setValue(true);
                        }
                    } else {
                        isShowErrorMessage.setValue(true);
                    }
                });
    }
}