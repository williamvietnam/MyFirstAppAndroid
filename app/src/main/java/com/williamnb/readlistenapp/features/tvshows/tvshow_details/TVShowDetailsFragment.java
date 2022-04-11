package com.williamnb.readlistenapp.features.tvshows.tvshow_details;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentTVShowDetailsBinding;
import com.williamnb.readlistenapp.databinding.LayoutEpisodesBottomSheetBinding;
import com.williamnb.readlistenapp.features.tvshows.adapter.EpisodesAdapter;
import com.williamnb.readlistenapp.features.tvshows.adapter.ImageSliderAdapter;

import java.util.Locale;

import io.grpc.internal.SharedResourceHolder;

public class TVShowDetailsFragment extends BaseFragment<FragmentTVShowDetailsBinding, TVShowDetailsViewModel> {

    private BottomSheetDialog episodesBottomSheetDialog;
    private LayoutEpisodesBottomSheetBinding layoutEpisodesBottomSheetBinding;

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
        viewBinding.imageBack.setOnClickListener(view -> findNavController().popBackStack());
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
                        viewBinding.setTvShowImageURL(
                                tvShowDetailsResponse.getTvShowDetails().getImagePath()
                        );
                        viewBinding.imageTvShow.setVisibility(View.VISIBLE);
                        viewBinding.setDescription(String.valueOf(HtmlCompat.fromHtml(
                                tvShowDetailsResponse.getTvShowDetails().getDescription(),
                                HtmlCompat.FROM_HTML_MODE_LEGACY)));
                        viewBinding.textDescription.setVisibility(View.VISIBLE);
                        viewBinding.textReadMore.setVisibility(View.VISIBLE);
                        viewBinding.textReadMore.setOnClickListener(view -> {
                            if (viewBinding.textReadMore.getText().toString().equals("Đọc tiếp")) {
                                viewBinding.textDescription.setMaxLines(Integer.MAX_VALUE);
                                viewBinding.textDescription.setEllipsize(null);
                                viewBinding.textReadMore.setText(R.string.read_less);
                            } else {
                                viewBinding.textDescription.setMaxLines(4);
                                viewBinding.textDescription.setEllipsize(TextUtils.TruncateAt.END);
                                viewBinding.textReadMore.setText(R.string.read_more);
                            }
                        });
                        viewBinding.setRating(
                                String.format(Locale.getDefault(), "%.2f",
                                        Double.parseDouble(tvShowDetailsResponse.getTvShowDetails().getRating()))
                        );
                        if (tvShowDetailsResponse.getTvShowDetails().getGenres() != null) {
                            viewBinding.setGenre(tvShowDetailsResponse.getTvShowDetails().getGenres()[0]);
                        } else {
                            viewBinding.setGenre("N/A");
                        }
                        viewBinding.setRuntime(tvShowDetailsResponse.getTvShowDetails().getRuntime() + " Min");
                        viewBinding.viewDivider1.setVisibility(View.VISIBLE);
                        viewBinding.layoutMisc.setVisibility(View.VISIBLE);
                        viewBinding.viewDivider2.setVisibility(View.VISIBLE);
                        viewBinding.buttonWebsite.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(tvShowDetailsResponse.getTvShowDetails().getUrl()));
                                startActivity(intent);
                            }
                        });
                        viewBinding.buttonWebsite.setVisibility(View.VISIBLE);
                        viewBinding.buttonEpisodes.setVisibility(View.VISIBLE);
                        viewBinding.buttonEpisodes.setOnClickListener(view -> {
                            if (episodesBottomSheetDialog == null) {
                                episodesBottomSheetDialog = new BottomSheetDialog(getContext());
                                layoutEpisodesBottomSheetBinding = DataBindingUtil.inflate(
                                        LayoutInflater.from(getContext()),
                                        R.layout.layout_episodes_bottom_sheet,
                                        getView().findViewById(R.id.episodesContainer),
                                        false);
                                episodesBottomSheetDialog.setContentView(layoutEpisodesBottomSheetBinding.getRoot());
                                layoutEpisodesBottomSheetBinding.episodesRcv.setAdapter(
                                        new EpisodesAdapter(tvShowDetailsResponse.getTvShowDetails().getEpisodes()));
                                layoutEpisodesBottomSheetBinding.textTitle.setText(
                                        String.format("Episodes | %s", getArguments().getString("name_tvShows"))
                                );
                                layoutEpisodesBottomSheetBinding.imageClose.setOnClickListener(view1 -> episodesBottomSheetDialog.dismiss());
                                // --- Optional section start --- //
                                FrameLayout frameLayout = episodesBottomSheetDialog.findViewById(
                                        com.google.android.material.R.id.design_bottom_sheet
                                );
                                if (frameLayout != null){
                                    BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
                                    bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                }
                                // --- Optional section end --- //
                                episodesBottomSheetDialog.show();
                            }
                        });
                        loadBasicTVShowDetails();
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

    private void loadBasicTVShowDetails() {
        assert getArguments() != null;
        viewBinding.setTvShowName(getArguments().getString("name_tvShows"));
        viewBinding.setNetworkCountry(getArguments().getString("network_tvShows") + " (" + getArguments().getString("country_tvShows") + ")");
        viewBinding.setStatus(getArguments().getString("status_tvShows"));
        viewBinding.setStartedDate(getArguments().getString("startDate_tvShows"));
        viewBinding.textName.setVisibility(View.VISIBLE);
        viewBinding.textNetworkCountry.setVisibility(View.VISIBLE);
        viewBinding.textStatus.setVisibility(View.VISIBLE);
        viewBinding.textStarted.setVisibility(View.VISIBLE);
    }
}
