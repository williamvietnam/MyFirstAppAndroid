package com.williamnb.readlistenapp.features.tvshows;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentMostPopularTvShowsBinding;
import com.williamnb.readlistenapp.features.tvshows.adapter.TVShowAdapter;
import com.williamnb.readlistenapp.remote.models.TVShow;

import java.util.ArrayList;
import java.util.List;

public class MostPopularTVShowsFragment extends BaseFragment<FragmentMostPopularTvShowsBinding, MostPopularTVShowsViewModel> {

    private List<TVShow> tvShows = new ArrayList<>();
    private TVShowAdapter tvShowAdapter;

    @Override
    public FragmentMostPopularTvShowsBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentMostPopularTvShowsBinding.inflate(inflater, container, false);
    }

    @Override
    public MostPopularTVShowsViewModel createViewModel() {
        return new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
    }

    @Override
    public void initializeView() {
        hideBottomNavigationView(true);
        viewBinding.tvShowsRcv.setHasFixedSize(true);
        tvShowAdapter = new TVShowAdapter(tvShows);
        viewBinding.tvShowsRcv.setAdapter(tvShowAdapter);
    }

    @Override
    public void initializeComponent() {
        getMostPopularTVShows();
    }

    @Override
    public void initializeEvents() {

    }

    @Override
    public void initializeData() {

    }

    private void getMostPopularTVShows() {
        viewBinding.setIsLoading(true);
        viewModel.getMostPopularTVShows(0).observe(this, mostPopularTVShowsResponse -> {
            viewBinding.setIsLoading(false);
            if (mostPopularTVShowsResponse != null) {
                if (mostPopularTVShowsResponse.getTvShows() != null) {
                    tvShows.addAll(mostPopularTVShowsResponse.getTvShows());
                    tvShowAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
