package com.williamnb.readlistenapp.ui.features.chat.chat_screen;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.williamnb.readlistenapp.base.BaseViewModel;
import com.williamnb.readlistenapp.data.local.models.ChatMessage;
import com.williamnb.readlistenapp.data.local.models.User;
import com.williamnb.readlistenapp.utilities.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class ChatScreenViewModel extends BaseViewModel {

    private final FirebaseFirestore database;
    private List<ChatMessage> chatMessages;
    private MutableLiveData<Boolean> isUpdateDataRecyclerView;
    private MutableLiveData<Boolean> isShowRecyclerView;

    public ChatScreenViewModel(@NonNull Application application) {
        super(application);
        this.database = FirebaseFirestore.getInstance();
        this.chatMessages = new ArrayList<>();
        this.isUpdateDataRecyclerView = new MutableLiveData<>();
        this.isShowRecyclerView = new MutableLiveData<>();
    }

    public List<ChatMessage> getChatMessages() {
        Log.d(ChatScreenFragment.class.getSimpleName(), "getChatMessages(): Size = " + this.chatMessages.size());
        return this.chatMessages;
    }

    public MutableLiveData<Boolean> getIsUpdateDataRecyclerView() {
        Log.d(ChatScreenFragment.class.getSimpleName(), "LiveDataUpdateRecyclerView(): " + this.isUpdateDataRecyclerView.getValue());
        return this.isUpdateDataRecyclerView;
    }

    public MutableLiveData<Boolean> getIsShowRecyclerView() {
        Log.d(ChatScreenFragment.class.getSimpleName(), "LiveDataShowRecyclerView(): " + this.isShowRecyclerView.getValue());
        return this.isShowRecyclerView;
    }

    public void sendMessage(@NonNull User receiverUser, String inputMessage) {
        HashMap<String, Object> message = new HashMap<>();
        message.put(Constants.KEY_SENDER_ID, getPreferenceManager().getString(Constants.KEY_USER_ID));
        message.put(Constants.KEY_RECEIVER_ID, receiverUser.getId());
        message.put(Constants.KEY_MESSAGE, inputMessage);
        message.put(Constants.KEY_TIMESTAMP, new Date());
        this.database.collection(Constants.KEY_COLLECTION_CHAT).add(message);
    }

    public void listenMessenger(@NonNull User receiverUser) {
        this.database.collection(Constants.KEY_COLLECTION_CHAT)
                .whereEqualTo(Constants.KEY_SENDER_ID, getPreferenceManager().getString(Constants.KEY_USER_ID))
                .whereEqualTo(Constants.KEY_RECEIVER_ID, receiverUser.getId())
                .addSnapshotListener(eventListener);
        this.database.collection(Constants.KEY_COLLECTION_CHAT)
                .whereEqualTo(Constants.KEY_SENDER_ID, receiverUser.getId())
                .whereEqualTo(Constants.KEY_RECEIVER_ID, getPreferenceManager().getString(Constants.KEY_USER_ID))
                .addSnapshotListener(eventListener);
    }

    private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
        if (error != null) {
            return;
        }
        if (value != null) {
            for (DocumentChange documentChange : value.getDocumentChanges()) {
                if (documentChange.getType() == DocumentChange.Type.ADDED) {
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.setSenderId(documentChange.getDocument().getString(Constants.KEY_SENDER_ID));
                    chatMessage.setReceiverId(documentChange.getDocument().getString(Constants.KEY_RECEIVER_ID));
                    chatMessage.setMessage(documentChange.getDocument().getString(Constants.KEY_MESSAGE));
                    chatMessage.setDateTime(getReadableDateTime(documentChange.getDocument().getDate(Constants.KEY_TIMESTAMP)));
                    chatMessage.setDateObject(documentChange.getDocument().getDate(Constants.KEY_TIMESTAMP));
                    chatMessages.add(chatMessage);
                }
            }
            chatMessages.sort(Comparator.comparing(ChatMessage::getDateObject));
            this.isUpdateDataRecyclerView.setValue(true);
            this.isShowRecyclerView.setValue(true);
        }
        this.isShowRecyclerView.setValue(false);
    };

    public Bitmap getBitmapFromEncodedString(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public String getReadableDateTime(Date date) {
        return new SimpleDateFormat("MMMM dd, yyyy - hh:mm a", Locale.getDefault()).format(date);
    }
}