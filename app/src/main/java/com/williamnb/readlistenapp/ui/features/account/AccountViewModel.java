package com.williamnb.readlistenapp.ui.features.account;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.models.AccountItem;
import com.williamnb.readlistenapp.utilities.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountViewModel extends BaseViewModel {
    private final List<AccountItem> itemList;
    private MutableLiveData<Boolean> logoutLiveData;
    private final String avatar;
    private final String fullName;
    private final String email;

    public AccountViewModel(@NonNull Application application) {
        super(application);
        itemList = new ArrayList<>();
        if (logoutLiveData == null) {
            this.logoutLiveData = new MutableLiveData<>();
        }
        this.avatar = getPreferenceManager().getString(Constants.KEY_IMAGE);
        this.fullName = getPreferenceManager().getString(Constants.KEY_NAME);
        this.email = getPreferenceManager().getString(Constants.KEY_EMAIL);
    }

    public List<AccountItem> getItemList() {
        this.itemList.clear();
        itemList.add(new AccountItem(Constants.ACCOUNT_ITEM_PRIVACY, "Tài khoản và bảo mật", "Thông tin tài khoản và bảo mật", R.drawable.ic_verified));
        itemList.add(new AccountItem(Constants.ACCOUNT_BACKUP_RESTORE, "Sao lưu và khôi phục", "Sao lưu dữ liệu và khôi phục", R.drawable.ic_back_up));
        itemList.add(new AccountItem(Constants.ACCOUNT_ITEM_NOTIFICATION, "Cài đặt thông báo", "Thông báo về tin nhắn, cuộc gọi", R.drawable.ic_notifications));
        itemList.add(new AccountItem(Constants.ACCOUNT_ITEM_LANGUAGE, "Cài đặt ngôn ngữ", "Thay đổi ngôn ngữ ứng dụng", R.drawable.ic_language));
        itemList.add(new AccountItem(Constants.ACCOUNT_ITEM_ABOUT_US, "Về chúng tôi", "Thông tin về ứng dụng và tác giả", R.drawable.ic_about_us));
        itemList.add(new AccountItem(Constants.ACCOUNT_ITEM_LOG_OUT, "Đăng xuất tài khoản", "Chuyển đổi tài khoản người dùng", R.drawable.ic_logout));
        return this.itemList;
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

    public String getAvatar() {
        return this.avatar;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getEmail() {
        Log.d(AccountFragment.class.getName(), this.email);
        return this.email;
    }
}