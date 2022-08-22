package com.williamnb.readlistenapp.ui.features.account.update_account_info;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Base64;

import androidx.annotation.NonNull;

import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.models.SettingItem;
import com.williamnb.readlistenapp.utilities.Constants;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UpdateAccountInfoViewModel extends BaseViewModel {

    private boolean isShowMoreDisplayInfo = true;
    private boolean isShowMoreChangePass = false;
    private boolean isShowMoreSettings = false;
    private String avatar;
    private String userName;
    private String email;
    private final List<SettingItem> settingItemList;

    public UpdateAccountInfoViewModel(@NonNull Application application) {
        super(application);
        this.avatar = getPreferenceManager().getString(Constants.KEY_IMAGE);
        this.userName = getPreferenceManager().getString(Constants.KEY_NAME);
        this.email = getPreferenceManager().getString(Constants.KEY_EMAIL);
        this.settingItemList = new ArrayList<>();
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public List<SettingItem> getSettingItemList() {
        this.settingItemList.clear();
        settingItemList.add(new SettingItem("Chia sẻ vị trí"));
        settingItemList.add(new SettingItem("Cho phép truy cập camera"));
        settingItemList.add(new SettingItem("Cho phép truy cập audio"));
        settingItemList.add(new SettingItem("Cho phép truy cập Wifi/4G"));
        return this.settingItemList;
    }

    public boolean isShowMoreDisplayInfo() {
        return this.isShowMoreDisplayInfo;
    }

    public boolean isShowMoreChangePass() {
        return this.isShowMoreChangePass;
    }

    public boolean isShowMoreSettings() {
        return this.isShowMoreSettings;
    }

    public void setShowMoreDisplayInfo(boolean showMoreDisplayInfo) {
        this.isShowMoreDisplayInfo = showMoreDisplayInfo;
    }

    public void setShowMoreChangePass(boolean showMoreChangePass) {
        this.isShowMoreChangePass = showMoreChangePass;
    }

    public void setShowMoreSettings(boolean showMoreSettings) {
        this.isShowMoreSettings = showMoreSettings;
    }

    public void putDisplayInformation(String avatar, String userName, String email) {
        this.avatar = avatar;
        this.userName = userName;
        this.email = email;
    }

    public void putChangePassword() {
        //todo
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