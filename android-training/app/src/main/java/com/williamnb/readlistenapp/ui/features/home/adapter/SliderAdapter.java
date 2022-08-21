package com.williamnb.readlistenapp.ui.features.home.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.williamnb.readlistenapp.base.BaseViewHolder;
import com.williamnb.readlistenapp.data.local.models.SliderItem;
import com.williamnb.readlistenapp.databinding.SlideItemContainerBinding;
import com.williamnb.readlistenapp.ui.features.home.HomeFragment;
import com.williamnb.readlistenapp.utilities.callback.HomeCallBack;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<SliderItem> sliderItems;
    private final ViewPager2 viewPager2;
    private final HomeCallBack callBack;
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };

    public SliderAdapter(List<SliderItem> sliderItems, ViewPager2 viewPager2, HomeCallBack callBack) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SlideItemContainerBinding binding = SlideItemContainerBinding.inflate(layoutInflater, parent, false);
        return new SliderHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
        if (position == sliderItems.size() - 2) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        if (sliderItems != null) {
            return sliderItems.size();
        }
        return 0;
    }

    class SliderHolder extends BaseViewHolder {
        private final SlideItemContainerBinding binding;

        public SliderHolder(@NonNull SlideItemContainerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * If you want to display image from the internet, you can put code here.
         * Using: glide or picasso
         */
        @Override
        public void onBind(int position) {
            super.onBind(position);
            SliderItem sliderItem = sliderItems.get(position);
            this.binding.imvSlide.setImageResource(sliderItem.getResImage());
            this.binding.getRoot().setOnClickListener(v -> {
                callBack.onBannerClicked(sliderItem);
                Log.d(HomeFragment.class.getSimpleName(), "BannerId: " + sliderItem.getId());
            });
        }

        @Override
        protected void clear() {
            Log.d(HomeFragment.class.getSimpleName(), "Cleared");
        }
    }
}