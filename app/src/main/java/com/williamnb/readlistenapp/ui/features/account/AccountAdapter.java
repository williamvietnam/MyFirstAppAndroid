package com.williamnb.readlistenapp.ui.features.account;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.AccountItem;
import com.williamnb.readlistenapp.databinding.LayoutAccountBinding;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final List<AccountItem> itemList;
    private final AccountCallBack callBack;

    public AccountAdapter(List<AccountItem> itemList, AccountCallBack callBack) {
        this.itemList = itemList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutAccountBinding binding = LayoutAccountBinding.inflate(inflater, parent, false);
        return new AccountAdapter.AccountViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (itemList != null) {
            return itemList.size();
        }
        return 0;
    }

    class AccountViewHolder extends BaseViewHolder {
        private final LayoutAccountBinding binding;

        public AccountViewHolder(@NonNull LayoutAccountBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            AccountItem item = itemList.get(position);
            this.binding.ivIcon.setImageResource(item.getIcon());
            this.binding.tvHeadline.setText(item.getTextHeadline());
            this.binding.tvDescription.setText(item.getTextDescription());
            this.binding.getRoot().setOnClickListener(view -> {
                callBack.onClicked(item);
                Log.d(AccountFragment.class.getName(), "debug: onClicked()...");
            });
        }

        @Override
        protected void clear() {
            Log.d(AccountFragment.class.getName(), "debug: cleared...");
        }
    }

    public interface AccountCallBack {
        void onClicked(AccountItem item);
    }
}