package com.williamnb.readlistenapp.ui.features.chat.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.User;
import com.williamnb.readlistenapp.databinding.ItemContainerUserBinding;
import com.williamnb.readlistenapp.ui.features.chat.users_list.UsersFragment;
import com.williamnb.readlistenapp.utilities.callback.ChatCallBack;

import java.util.List;

/**
 * Author: William Giang Nguyen | 15/08/2022
 */
public class UsersAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<User> userList;
    private final ChatCallBack callBack;

    public UsersAdapter(List<User> userList, ChatCallBack callBack) {
        this.userList = userList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemContainerUserBinding binding = ItemContainerUserBinding.inflate(layoutInflater, parent, false);
        return new UsersAdapter.UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (userList != null) {
            return userList.size();
        }
        return 0;
    }

    class UserViewHolder extends BaseViewHolder {
        private final ItemContainerUserBinding binding;

        public UserViewHolder(@NonNull ItemContainerUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            User item = userList.get(position);
            binding.textName.setText(item.getName());
            binding.textEmail.setText(item.getEmail());
            binding.imageProfile.setImageBitmap(getUserImage(item.getImage()));
            binding.getRoot().setOnClickListener(view -> {
                callBack.onUserClicked(item);
                Log.d(UsersFragment.class.getSimpleName(), "onUserClicked() + " + item.getEmail());
            });
        }

        @Override
        protected void clear() {
            binding.textName.setText("");
            binding.textEmail.setText("");
            binding.imageProfile.setImageBitmap(null);
        }
    }

    private Bitmap getUserImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}