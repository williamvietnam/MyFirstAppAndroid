package com.williamnb.readlistenapp.ui.features.account.update_account_info;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.SettingItem;
import com.williamnb.readlistenapp.databinding.LayoutUpdateAccountInfoBinding;

import java.util.List;

public class UpdateAccountInfoAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<SettingItem> settingItemList;
    private final UpdateAccountInfoCallBack callBack;

    public UpdateAccountInfoAdapter(List<SettingItem> settingItemList, UpdateAccountInfoCallBack callBack) {
        this.settingItemList = settingItemList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutUpdateAccountInfoBinding binding = LayoutUpdateAccountInfoBinding.inflate(inflater, parent, false);
        return new UpdateAccountInfoAdapter.UpdateAccountInfoHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (settingItemList != null) {
            return settingItemList.size();
        }
        return 0;
    }

    class UpdateAccountInfoHolder extends BaseViewHolder {
        private final LayoutUpdateAccountInfoBinding binding;

        public UpdateAccountInfoHolder(@NonNull LayoutUpdateAccountInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            SettingItem item = settingItemList.get(position);
            this.binding.text.setText(item.getText());
            this.binding.btnSwitch.setChecked(item.isSwitch());
            this.binding.btnSwitch.setOnClickListener(v -> {
                item.setSwitch(!item.isSwitch());
                callBack.onClicked(item);
            });
        }

        @Override
        protected void clear() {
        }
    }

    public interface UpdateAccountInfoCallBack {
        void onClicked(SettingItem item);
    }
}