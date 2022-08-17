package com.williamnb.readlistenapp.ui.features.chat.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.ChatMessage;
import com.williamnb.readlistenapp.databinding.ItemContainerReceivedMessageBinding;
import com.williamnb.readlistenapp.databinding.ItemContainerSentMessageBinding;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<ChatMessage> chatMessages;
    private final Bitmap receiverProfileImage;
    private final String senderId;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;

    public ChatAdapter(List<ChatMessage> chatMessages, Bitmap receiverProfileImage, String senderId) {
        this.chatMessages = chatMessages;
        this.receiverProfileImage = receiverProfileImage;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_SENT) {
            ItemContainerSentMessageBinding binding = ItemContainerSentMessageBinding.inflate(layoutInflater, parent, false);
            return new ChatAdapter.SentMessageViewHolder(binding);
        } else {
            ItemContainerReceivedMessageBinding binding = ItemContainerReceivedMessageBinding.inflate(layoutInflater, parent, false);
            return new ChatAdapter.ReceivedMessageViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_SENT) {
            ((SentMessageViewHolder) holder).onBind(position);
        } else {
            ((ReceivedMessageViewHolder) holder).onBind(position);
        }
    }

    @Override
    public int getItemCount() {
        if (chatMessages != null) {
            return chatMessages.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (chatMessages.get(position).getSenderId().equals(senderId)) {
            return VIEW_TYPE_SENT;
        } else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    class SentMessageViewHolder extends BaseViewHolder {
        private final ItemContainerSentMessageBinding binding;

        SentMessageViewHolder(@NonNull ItemContainerSentMessageBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            this.binding = itemContainerSentMessageBinding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            ChatMessage item = chatMessages.get(position);
            this.binding.textMessage.setText(item.getMessage());
            this.binding.textDateTime.setText(item.getDateTime());
        }

        @Override
        protected void clear() {
            this.binding.textMessage.setText("");
            this.binding.textDateTime.setText("");
        }
    }

    class ReceivedMessageViewHolder extends BaseViewHolder {
        private final ItemContainerReceivedMessageBinding binding;

        ReceivedMessageViewHolder(@NonNull ItemContainerReceivedMessageBinding itemContainerReceivedMessageBinding) {
            super(itemContainerReceivedMessageBinding.getRoot());
            this.binding = itemContainerReceivedMessageBinding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            ChatMessage item = chatMessages.get(position);
            this.binding.textMessage.setText(item.getMessage());
            this.binding.textDateTime.setText(item.getDateTime());
            this.binding.imageProfile.setImageBitmap(receiverProfileImage);
        }

        @Override
        protected void clear() {
            this.binding.textMessage.setText("");
            this.binding.textDateTime.setText("");
            this.binding.imageProfile.setImageBitmap(null);
        }
    }
}