package com.williamnb.readlistenapp.features.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentHomeBinding;
import com.williamnb.readlistenapp.features.home.adapter.ItemFeaturedGamesAdapter;
import com.williamnb.readlistenapp.features.home.adapter.ItemFeaturedNewsAdapter;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
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
        showListGames();
        showListNews();
    }

    @Override
    public void initializeEvents() {

    }

    @Override
    public void initializeData() {
    }

    private void showListGames(){
        ItemFeaturedGamesAdapter itemFeaturedGamesAdapter = new ItemFeaturedGamesAdapter(viewModel.mockDataGames());
        viewBinding.rcvGames.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        viewBinding.rcvGames.setAdapter(itemFeaturedGamesAdapter);
    }

    private void showListNews(){
        ItemFeaturedNewsAdapter itemFeaturedNewsAdapter = new ItemFeaturedNewsAdapter(viewModel.mockDataNews());
        viewBinding.rcvNews.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        viewBinding.rcvNews.setAdapter(itemFeaturedNewsAdapter);
    }
}
