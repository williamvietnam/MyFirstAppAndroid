package com.williamnb.readlistenapp.ui.features.home;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentHomeBinding;
import com.williamnb.readlistenapp.ui.features.home.adapter.ItemFeaturedGamesAdapter;
import com.williamnb.readlistenapp.ui.features.home.adapter.ItemFeaturedNewsAdapter;
import com.williamnb.readlistenapp.ui.features.home.adapter.SliderAdapter;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel>
        implements View.OnClickListener {

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
        hideBottomNavigationView(false);
    }

    @Override
    public void initializeComponent() {
        showSlide();
        showListGames();
        showListNews();
    }

    @Override
    public void initializeEvents() {
        viewBinding.menuAction.btnMessage.setOnClickListener(this);
        viewBinding.menuAction.btnGames.setOnClickListener(this);
        viewBinding.menuAction.btnTvShows.setOnClickListener(this);
        viewBinding.menuAction.btnNews.setOnClickListener(this);
    }

    @Override
    public void initializeData() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGames: {
                Toast.makeText(getContext(), "Tính năng này đang phát triển", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btnTvShows: {
                findNavController().navigate(R.id.actionHomeToMostPopularTVShows);
                break;
            }
            case R.id.btnMessage: {
                findNavController().navigate(R.id.actionSignIn);
                break;
            }
            case R.id.btnNews: {
                findNavController().navigate(R.id.actionHomeToNews);
                break;
            }
        }
    }

    private final Runnable sliderRunnable = () -> {
//            viewBinding.viewPager2.setCurrentItem(viewBinding.viewPager2.getCurrentItem() + 1);
    };

    private void showSlide() {
        viewBinding.viewPager2.setAdapter(new SliderAdapter(viewModel.getDataSlide(), viewBinding.viewPager2));
        viewBinding.viewPager2.setClipToPadding(false);
        viewBinding.viewPager2.setClipChildren(false);
        viewBinding.viewPager2.setOffscreenPageLimit(3);
        viewBinding.viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(24));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
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
