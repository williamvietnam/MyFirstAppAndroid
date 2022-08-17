package com.williamnb.readlistenapp.ui.features.chat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentChatMainBinding;
import com.williamnb.readlistenapp.ui.features.login.SignInActivity;
import com.williamnb.readlistenapp.ui.features.main.MainActivity;

public class ChatMainFragment extends BaseFragment<FragmentChatMainBinding, ChatMainViewModel> {

    private MainActivity mainActivity;

    @Override
    public FragmentChatMainBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        mainActivity = new MainActivity();
        return FragmentChatMainBinding.inflate(inflater, container, false);
    }

    @Override
    public ChatMainViewModel createViewModel() {
        return new ViewModelProvider(this).get(ChatMainViewModel.class);
    }

    @Override
    public void initializeView() {
        hideBottomNavigationView(true);
        viewModel.getUserInformation();
    }

    @Override
    public void initializeComponent() {
    }

    @Override
    public void initializeEvents() {
        viewBinding.btnBack.setOnClickListener(v -> {
            findNavController().popBackStack();
            Log.d(ChatMainFragment.class.getSimpleName(), "Back clicked");
        });
        viewBinding.fabNewChat.setOnClickListener(view -> findNavController().navigate(R.id.actionChatMainToUsersScreen));
        viewBinding.imageSignOut.setOnClickListener(view -> {
            viewModel.signOut();
            Log.d(ChatMainFragment.class.getSimpleName(), "Signed out");
        });
    }

    @Override
    public void initializeData() {
        viewModel.getToken();

        final Observer<String> updateNameObserver = new Observer<String>() {
            @Override
            public void onChanged(String name) {
                viewBinding.textName.setText(name);
            }
        };
        viewModel.getUserName().observe(this, updateNameObserver);

        final Observer<String> updateAvatarObserver = new Observer<String>() {
            @Override
            public void onChanged(String avatar) {
                byte[] bytes = Base64.decode(avatar, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                viewBinding.imageProfile.setImageBitmap(bitmap);
            }
        };
        viewModel.getImageAvatar().observe(this, updateAvatarObserver);

        final Observer<Boolean> checkSignedOutObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSignedOut) {
                if (isSignedOut) {
                    Intent intent = new Intent(requireActivity(), SignInActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                }
            }
        };
        viewModel.getIsSignedOut().observe(this, checkSignedOutObserver);
    }
}