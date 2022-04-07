package com.williamnb.readlistenapp.features.chat;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentChatMainBinding;

public class ChatMainFragment extends BaseFragment<FragmentChatMainBinding, ChatMainViewModel> {
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
    }

    @Override
    public void initializeComponent() {

    }

    @Override
    public void initializeEvents() {

    }

    @Override
    public void initializeData() {

    }
}
