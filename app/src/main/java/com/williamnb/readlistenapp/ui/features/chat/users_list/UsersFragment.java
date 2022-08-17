package com.williamnb.readlistenapp.ui.features.chat.users_list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.data.local.models.User;
import com.williamnb.readlistenapp.databinding.FragmentUsersBinding;
import com.williamnb.readlistenapp.ui.features.chat.adapter.UsersAdapter;
import com.williamnb.readlistenapp.utilities.Constants;
import com.williamnb.readlistenapp.utilities.callback.ChatCallBack;

import java.util.List;

public class UsersFragment extends BaseFragment<FragmentUsersBinding, UsersViewModel>
        implements ChatCallBack {

    private UsersAdapter usersAdapter;

    @Override
    public FragmentUsersBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentUsersBinding.inflate(inflater, container, false);
    }

    @Override
    public UsersViewModel createViewModel() {
        return new ViewModelProvider(this).get(UsersViewModel.class);
    }

    @Override
    public void initializeView() {
        hideBottomNavigationView(true);
    }

    @Override
    public void initializeComponent() {
        final Observer<List<User>> bindUserAdapter = new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                UsersFragment.this.usersAdapter = new UsersAdapter(users, UsersFragment.this);
                viewBinding.usersRcv.setAdapter(usersAdapter);
                viewBinding.usersRcv.setVisibility(View.VISIBLE);
            }
        };
        viewModel.getBindUserAdapter().observe(this, bindUserAdapter);

        final Observer<Boolean> showErrorMessage = new Observer<Boolean>() {
            @Override
            public void onChanged(@NonNull Boolean isShowErrorMessage) {
                if (isShowErrorMessage) {
                    showErrorMessage();
                    Log.d(UsersFragment.class.getSimpleName(), "User does not exist");
                } else {
                    Log.d(UsersFragment.class.getSimpleName(), "Accept User SUCCESS!");
                }
            }
        };
        viewModel.getIsShowErrorMessage().observe(this, showErrorMessage);

        final Observer<Boolean> showLoading = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                loading(isLoading);
            }
        };
        viewModel.getIsShowLoading().observe(this, showLoading);
    }

    @Override
    public void initializeEvents() {
        viewBinding.btnBack.setOnClickListener(view -> findNavController().popBackStack());
    }

    @Override
    public void initializeData() {
        viewModel.getUsers();
    }

    @Override
    public void onUserClicked(User user) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.KEY_USER, user);
        findNavController().navigate(R.id.actionUsersToChatScreen, bundle);
    }

    private void showErrorMessage() {
        viewBinding.textErrorMessage.setText(String.format("%s", "Người dùng không tồn tại"));
        viewBinding.textErrorMessage.setVisibility(View.VISIBLE);
    }

    private void loading(@NonNull Boolean isLoading) {
        if (isLoading) {
            viewBinding.progressBar.setVisibility(View.VISIBLE);
        } else {
            viewBinding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}