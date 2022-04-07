package com.williamnb.readlistenapp.features.chat;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentChatBinding;

public class ChatFragment extends BaseFragment<FragmentChatBinding, ChatViewModel> {
    @Override
    public FragmentChatBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentChatBinding.inflate(inflater, container, false);
    }

    @Override
    public ChatViewModel createViewModel() {
        return new ViewModelProvider(this).get(ChatViewModel.class);
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
