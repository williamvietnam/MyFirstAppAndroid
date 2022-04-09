package com.williamnb.readlistenapp.features.tvshows.tvshow_details;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentTVShowDetailsBinding;
import com.williamnb.readlistenapp.features.tvshows.adapter.ImageSliderAdapter;

public class TVShowDetailsFragment extends BaseFragment<FragmentTVShowDetailsBinding, TVShowDetailsViewModel> {
    @Override
    public FragmentTVShowDetailsBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentTVShowDetailsBinding.inflate(inflater, container, false);
    }

    @Override
    public TVShowDetailsViewModel createViewModel() {
        return new ViewModelProvider(this).get(TVShowDetailsViewModel.class);
    }

    @Override
    public void initializeView() {

    }

    @Override
    public void initializeComponent() {
        getTVShowDetails();
    }

    @Override
    public void initializeEvents() {

    }

    @Override
    public void initializeData() {

    }

    private void getTVShowDetails() {
        viewBinding.setIsLoading(true);
        String tvShowId = String.valueOf(getArguments().getInt("id_tvShows"));
        Log.d("test", tvShowId);
        viewModel.getTVShowDetails(tvShowId).observe(
                this, tvShowDetailsResponse -> {
                    viewBinding.setIsLoading(false);
                    if (tvShowDetailsResponse.getTvShowDetails() != null) {
                        if (tvShowDetailsResponse.getTvShowDetails().getPictures() != null) {
                            loadImageSlider(tvShowDetailsResponse.getTvShowDetails().getPictures());
                        }
                    }
                });
    }

    private void loadImageSlider(String[] sliderImages) {
        viewBinding.sliderViewPager.setOffscreenPageLimit(1);
        viewBinding.sliderViewPager.setAdapter(new ImageSliderAdapter(sliderImages));
        viewBinding.sliderViewPager.setVisibility(View.VISIBLE);
        viewBinding.viewFadingEdge.setVisibility(View.VISIBLE);
        setupSliderIndicators(sliderImages.length);
        viewBinding.sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentSliderIndicator(position);
            }
        });
    }

    private void setupSliderIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getContext(), R.drawable.background_slider_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            viewBinding.layoutSliderIndicator.addView(indicators[i]);
        }
        viewBinding.layoutSliderIndicator.setVisibility(View.VISIBLE);
        setCurrentSliderIndicator(0);
    }

    private void setCurrentSliderIndicator(int position) {
        int childCount = viewBinding.layoutSliderIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) viewBinding.layoutSliderIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getContext(), R.drawable.background_slider_indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getContext(), R.drawable.background_slider_indicator_inactive));
            }
        }
    }
}
