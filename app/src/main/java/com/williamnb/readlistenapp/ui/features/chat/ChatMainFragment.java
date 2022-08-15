package com.williamnb.readlistenapp.ui.features.chat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentChatMainBinding;
import com.williamnb.readlistenapp.data.local.preferences.PreferenceManager;
import com.williamnb.readlistenapp.utilities.Constants;

import java.util.HashMap;

public class ChatMainFragment extends BaseFragment<FragmentChatMainBinding, ChatMainViewModel> {

    private PreferenceManager preferenceManager;

    @Override
    public FragmentChatMainBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentChatMainBinding.inflate(inflater, container, false);
    }

    @Override
    public ChatMainViewModel createViewModel() {
        return new ViewModelProvider(this).get(ChatMainViewModel.class);
    }

    @Override
    public void initializeView() {
        hideBottomNavigationView(true);
        preferenceManager = new PreferenceManager(requireContext());
    }

    @Override
    public void initializeComponent() {

    }

    @Override
    public void initializeEvents() {
        viewBinding.fabNewChat.setOnClickListener(view -> findNavController().navigate(R.id.actionChatMainToUsersScreen));
        viewBinding.imageSignOut.setOnClickListener(view -> signOut());
    }

    @Override
    public void initializeData() {
        loadUserDetails();
        getToken();
    }

    private void loadUserDetails() {
        viewBinding.textName.setText(preferenceManager.getString(Constants.KEY_NAME));
        byte[] bytes = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        viewBinding.imageProfile.setImageBitmap(bitmap);
    }

    private void getToken() {
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateToken);
    }

    private void updateToken(String token) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        documentReference.update(Constants.KEY_FCM_TOKEN, token)
//                .addOnSuccessListener(unused -> showToast("Token đã cập nhật thành công"))
                .addOnFailureListener(e -> viewModel.showToast("Không thể cập nhật token", getContext()));
    }

    private void signOut() {
        viewModel.showToast("Đang đăng xuất...", getContext());
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        HashMap<String, Object> updates = new HashMap<>();
        updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    preferenceManager.clear();
                    findNavController().navigate(R.id.actionChatMainToHome);
                })
                .addOnFailureListener(e -> viewModel.showToast("Không thể đăng xuất", getContext()));
    }
}
