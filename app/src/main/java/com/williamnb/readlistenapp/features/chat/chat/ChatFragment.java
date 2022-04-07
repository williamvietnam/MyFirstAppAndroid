package com.williamnb.readlistenapp.features.chat.chat;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentChatMainBinding;

public class ChatFragment extends BaseFragment<FragmentChatMainBinding, ChatViewModel> {
    @Override
    public FragmentChatMainBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentChatMainBinding.inflate(inflater, container, false);
    }

    @Override
    public ChatViewModel createViewModel() {
        return new ViewModelProvider(this).get(ChatViewModel.class);
    }

    @Override
    public void initializeView() {

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
