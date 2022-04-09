package com.williamnb.readlistenapp.features.chat.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.common.UserListener;
import com.williamnb.readlistenapp.domain.model.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private final List<User> userList;
    private final UserListener userListener;

    public UsersAdapter(List<User> userList, UserListener userListener) {
        this.userList = userList;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_container_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textName.setText(user.getName());
        holder.textEmail.setText(user.getEmail());
        holder.imageProfile.setImageBitmap(getUserImage(user.getImage()));
        holder.layoutUser.setOnClickListener(view -> userListener.onUserClicked(user));
    }

    @Override
    public int getItemCount() {
        if (userList != null) {
            return userList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final RoundedImageView imageProfile;
        private final TextView textName;
        private final TextView textEmail;
        private final ConstraintLayout layoutUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProfile = itemView.findViewById(R.id.imageProfile);
            textName = itemView.findViewById(R.id.textName);
            textEmail = itemView.findViewById(R.id.textEmail);
            layoutUser = itemView.findViewById(R.id.layoutUser);
        }
    }

    private Bitmap getUserImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
//public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
//
//    private final List<User> userList;
//
//    public UsersAdapter(List<User> userList) {
//        this.userList = userList;
//    }
//
//    @NonNull
//    @Override
//    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        ItemContainerUserBinding itemContainerUserBinding = ItemContainerUserBinding.inflate(
//                LayoutInflater.from(parent.getContext()),
//                parent,
//                false
//        );
//
//        return new UsersViewHolder(itemContainerUserBinding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
//        holder.setUserData(userList.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return userList.size();
//    }
//
//    class UsersViewHolder extends RecyclerView.ViewHolder {
//
//        ItemContainerUserBinding viewBinding;
//
//        public UsersViewHolder(ItemContainerUserBinding itemContainerUserBinding) {
//            super(itemContainerUserBinding.getRoot());
//        }
//
//        void setUserData(User user) {
//            viewBinding.textName.setText("abc");
//            viewBinding.textEmail.setText("abc");
//            viewBinding.imageProfile.setImageBitmap(getUserImage(user.image));
//        }
//    }
//
//    private Bitmap getUserImage(String encodedImage) {
//        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
//        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//    }
//}
