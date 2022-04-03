package com.williamnb.readlistenapp.features.home;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentHomeBinding;
import com.williamnb.readlistenapp.features.home.adapter.ItemFeaturedGamesAdapter;
import com.williamnb.readlistenapp.features.home.adapter.ItemFeaturedNewsAdapter;
import com.williamnb.readlistenapp.features.home.adapter.SliderAdapter;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    private final Handler sliderHandler = new Handler();

    @Override
    public FragmentHomeBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
    }

    @Override
    public HomeViewModel createViewModel() {
        return new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public void initializeView() {
    }

    @Override
    public void initializeComponent() {
        showSlide();
        showListGames();
        showListNews();
    }

    @Override
    public void initializeEvents() {
    }

    @Override
    public void initializeData() {
    }

    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
//            viewBinding.viewPager2.setCurrentItem(viewBinding.viewPager2.getCurrentItem() + 1);
        }
    };

    private void showSlide() {
        viewBinding.viewPager2.setAdapter(new SliderAdapter(viewModel.getDataSlide(), viewBinding.viewPager2));
        viewBinding.viewPager2.setClipToPadding(false);
        viewBinding.viewPager2.setClipChildren(false);
        viewBinding.viewPager2.setOffscreenPageLimit(3);
        viewBinding.viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(24));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewBinding.viewPager2.setPageTransformer(compositePageTransformer);

        viewBinding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000);
            }
        });
    }

    private void showListGames() {
        ItemFeaturedGamesAdapter itemFeaturedGamesAdapter = new ItemFeaturedGamesAdapter(viewModel.mockDataGames());
        viewBinding.rcvGames.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        viewBinding.rcvGames.setAdapter(itemFeaturedGamesAdapter);
    }

    private void showListNews() {
        ItemFeaturedNewsAdapter itemFeaturedNewsAdapter = new ItemFeaturedNewsAdapter(viewModel.mockDataNews());
        viewBinding.rcvNews.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        viewBinding.rcvNews.setAdapter(itemFeaturedNewsAdapter);
    }
}
