package com.williamnb.readlistenapp.ui.features.welcome;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.Welcome;
import com.williamnb.readlistenapp.databinding.ItemWelcomeBinding;

import java.util.List;

public class WelcomeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<Welcome> welcomeList;

    public WelcomeAdapter(List<Welcome> welcomeList) {
        Log.d(WelcomeActivity.class.getSimpleName(), "Create WelcomeAdapter");
        this.welcomeList = welcomeList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemWelcomeBinding binding = ItemWelcomeBinding.inflate(inflater, parent, false);
        return new WelcomeAdapter.WelcomeHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (welcomeList != null) {
            return welcomeList.size();
        }
        return 0;
    }

    class WelcomeHolder extends BaseViewHolder {

        private final ItemWelcomeBinding binding;

        public WelcomeHolder(@NonNull ItemWelcomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            Welcome item = welcomeList.get(position);
            binding.text.setText(item.getText());
            binding.image.setImageResource(item.getImage());
        }

        @Override
        protected void clear() {
            Log.d(WelcomeActivity.class.getSimpleName(), "cleared");
        }
    }
}