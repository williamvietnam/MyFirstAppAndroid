package com.williamnb.readlistenapp.ui.features.tvshows.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.databinding.ItemContainerSliderImageBinding;
import com.williamnb.readlistenapp.ui.features.tvshows.tvshow_details.TVShowDetailsFragment;

public class ImageSliderAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final String[] sliderImages;

    public ImageSliderAdapter(String[] sliderImages) {
        this.sliderImages = sliderImages;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemContainerSliderImageBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_container_slider_image, parent, false);
        return new ImageSliderAdapter.ImageSliderHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (sliderImages != null) {
            return sliderImages.length;
        }
        return 0;
    }

    class ImageSliderHolder extends BaseViewHolder {
        private final ItemContainerSliderImageBinding binding;

        public ImageSliderHolder(@NonNull ItemContainerSliderImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            String imageURL = sliderImages[position];
            binding.setImageURL(imageURL);
        }

        @Override
        protected void clear() {
            Log.d(TVShowDetailsFragment.class.getSimpleName(), "cleared");
        }
    }
}