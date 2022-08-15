package com.williamnb.readlistenapp.ui.features.chat.chat_screen;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentChatScreenBinding;
import com.williamnb.readlistenapp.data.local.models.ChatMessage;
import com.williamnb.readlistenapp.data.local.models.User;
import com.williamnb.readlistenapp.ui.features.chat.adapter.ChatAdapter;
import com.williamnb.readlistenapp.utilities.Constants;
import com.williamnb.readlistenapp.data.local.preferences.PreferenceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ChatScreenFragment extends BaseFragment<FragmentChatScreenBinding, ChatScreenViewModel> {

    private User receiverUser;
    private List<ChatMessage> chatMessages;
    private ChatAdapter chatAdapter;
    private PreferenceManager preferenceManager;
    private FirebaseFirestore database;

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

    }

    @Override
    public void initializeEvents() {
        viewBinding.layoutSend.setOnClickListener(view -> sendMessage());
        viewBinding.buttonBack.setOnClickListener(view -> findNavController().popBackStack());
    }

    @Override
    public void initializeData() {
        loadReceiverDetails();

        preferenceManager = new PreferenceManager(requireContext());
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages,
                viewModel.getBitmapFromEncodedString(receiverUser.getImage()),
                preferenceManager.getString(Constants.KEY_USER_ID)
        );
        viewBinding.chatRcv.setAdapter(chatAdapter);
        database = FirebaseFirestore.getInstance();

        listenMessenger();
    }

    private void sendMessage() {
        HashMap<String, Object> message = new HashMap<>();
        message.put(Constants.KEY_SENDER_ID, preferenceManager.getString(Constants.KEY_USER_ID));
        message.put(Constants.KEY_RECEIVER_ID, receiverUser.getId());
        message.put(Constants.KEY_MESSAGE, viewBinding.inputMessage.getText().toString());
        message.put(Constants.KEY_TIMESTAMP, new Date());
        database.collection(Constants.KEY_COLLECTION_CHAT).add(message);
        viewBinding.inputMessage.setText(null);
    }

    private void listenMessenger() {
        database.collection(Constants.KEY_COLLECTION_CHAT)
                .whereEqualTo(Constants.KEY_SENDER_ID, preferenceManager.getString(Constants.KEY_USER_ID))
                .whereEqualTo(Constants.KEY_RECEIVER_ID, receiverUser.getId())
                .addSnapshotListener(eventListener);
        database.collection(Constants.KEY_COLLECTION_CHAT)
                .whereEqualTo(Constants.KEY_SENDER_ID, receiverUser.getId())
                .whereEqualTo(Constants.KEY_RECEIVER_ID, preferenceManager.getString(Constants.KEY_USER_ID))
                .addSnapshotListener(eventListener);
    }

    @SuppressLint("NotifyDataSetChanged")
    private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
        if (error != null) {
            return;
        }
        if (value != null) {
            int count = chatMessages.size();
            for (DocumentChange documentChange : value.getDocumentChanges()) {
                if (documentChange.getType() == DocumentChange.Type.ADDED) {
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.setSenderId(documentChange.getDocument().getString(Constants.KEY_SENDER_ID));
                    chatMessage.setReceiverId(documentChange.getDocument().getString(Constants.KEY_RECEIVER_ID));
                    chatMessage.setMessage(documentChange.getDocument().getString(Constants.KEY_MESSAGE));
                    chatMessage.setDateTime(viewModel.getReadableDateTime(documentChange.getDocument().getDate(Constants.KEY_TIMESTAMP)));
                    chatMessage.setDateObject(documentChange.getDocument().getDate(Constants.KEY_TIMESTAMP));
                    chatMessages.add(chatMessage);
                }
            }
            Collections.sort(chatMessages, (obj1, obj2) -> obj1.getDateObject().compareTo(obj2.getDateObject()));
            if (count == 0) {
                chatAdapter.notifyDataSetChanged();
            } else {
                chatAdapter.notifyItemRangeInserted(chatMessages.size(), chatMessages.size());
                viewBinding.chatRcv.smoothScrollToPosition(chatMessages.size() - 1);
            }
            viewBinding.chatRcv.setVisibility(View.VISIBLE);
        }
        viewBinding.progressBar.setVisibility(View.GONE);
    };

    private void loadReceiverDetails() {
        assert getArguments() != null;
        receiverUser = (User) getArguments().getSerializable(Constants.KEY_USER);
        viewBinding.textName.setText(receiverUser.getName());
    }
}
