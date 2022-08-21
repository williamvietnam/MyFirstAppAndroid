package com.williamnb.readlistenapp.ui.features.chat.chat_screen;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.data.local.models.User;
import com.williamnb.readlistenapp.databinding.FragmentChatScreenBinding;
import com.williamnb.readlistenapp.ui.features.chat.adapter.ChatAdapter;
import com.williamnb.readlistenapp.utilities.Constants;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class ChatScreenFragment extends BaseFragment<FragmentChatScreenBinding, ChatScreenViewModel> {

    private User receiverUser;
    private ChatAdapter chatAdapter;

    @Override
    public FragmentChatScreenBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentChatScreenBinding.inflate(inflater, container, false);
    }

    @Override
    public ChatScreenViewModel createViewModel() {
        return new ViewModelProvider(this).get(ChatScreenViewModel.class);
    }

    @Override
    public void initializeView() {
        hideBottomNavigationView(true);
    }

    @Override
    public void initializeComponent() {
        final Observer<Boolean> updateDataRecyclerViewObserver = isUpdate -> {
            if (isUpdate) {
                updateChatRecycleView();
                Log.d(ChatScreenFragment.class.getSimpleName(), "updateChatRecycleView()...Success");
            } else {
                Log.d(ChatScreenFragment.class.getSimpleName(), "updateChatRecycleView()...Failure");
            }
        };
        viewModel.getIsUpdateDataRecyclerView().observe(this, updateDataRecyclerViewObserver);

        final Observer<Boolean> showRecyclerViewObserver = isShowRecyclerView -> {
            if (isShowRecyclerView) {
                viewBinding.chatRcv.setVisibility(View.VISIBLE);
            }
            viewBinding.progressBar.setVisibility(View.GONE);
        };
        viewModel.getIsShowRecyclerView().observe(this, showRecyclerViewObserver);
    }

    @Override
    public void initializeEvents() {
        viewBinding.layoutSend.setOnClickListener(view -> {
            viewModel.sendMessage(this.receiverUser, viewBinding.inputMessage.getText().toString());
            viewBinding.inputMessage.setText(null);
            Log.d(ChatScreenFragment.class.getSimpleName(), "sendMessage()...");
        });
        viewBinding.buttonBack.setOnClickListener(view -> {
            findNavController().popBackStack();
            Log.d(ChatScreenFragment.class.getSimpleName(), "popBackStack()...");
        });
    }

    @Override
    public void initializeData() {
        loadReceiverDetails();
        chatAdapter = new ChatAdapter(viewModel.getChatMessages(),
                viewModel.getBitmapFromEncodedString(receiverUser.getImage()),
                viewModel.getPreferenceManager().getString(Constants.KEY_USER_ID)
        );
        viewBinding.chatRcv.setAdapter(chatAdapter);
        viewModel.listenMessenger(this.receiverUser);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void updateChatRecycleView() {
        int count = viewModel.getChatMessages().size();
        if (count == 0) {
            chatAdapter.notifyDataSetChanged();
        } else {
            chatAdapter.notifyItemRangeInserted(count, count);
            viewBinding.chatRcv.smoothScrollToPosition(count - 1);
        }
    }

    private void loadReceiverDetails() {
        assert getArguments() != null;
        receiverUser = (User) getArguments().getSerializable(Constants.KEY_USER);
        viewBinding.textName.setText(receiverUser.getName());
    }
}