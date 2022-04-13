package com.williamnb.readlistenapp.features.tvshows.most_popular_tvshows;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.common.TVShowsListener;
import com.williamnb.readlistenapp.databinding.FragmentMostPopularTvShowsBinding;
import com.williamnb.readlistenapp.features.tvshows.adapter.TVShowAdapter;
import com.williamnb.readlistenapp.remote.models.TVShow;

import java.util.ArrayList;
import java.util.List;

public class MostPopularTVShowsFragment extends BaseFragment<FragmentMostPopularTvShowsBinding, MostPopularTVShowsViewModel>
        implements TVShowsListener {

    private final List<TVShow> tvShows = new ArrayList<>();
    private TVShowAdapter tvShowAdapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;

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
        tvShowAdapter = new TVShowAdapter(tvShows, this);
        viewBinding.tvShowsRcv.setAdapter(tvShowAdapter);
        viewBinding.tvShowsRcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!viewBinding.tvShowsRcv.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePages) {
                        currentPage += 1;
                        getMostPopularTVShows();
                    }
                }
            }
        });
    }

    @Override
    public void initializeComponent() {
        getMostPopularTVShows();
    }

    @Override
    public void initializeEvents() {
        viewBinding.btnBack.setOnClickListener(view -> findNavController().popBackStack());
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("tvShow", tvShow);
        findNavController().navigate(R.id.actionMostPopularToTVShowDetails, bundle);
    }

    private void getMostPopularTVShows() {
        toggleLoading();
        viewModel.getMostPopularTVShows(currentPage).observe(this, mostPopularTVShowsResponse -> {
            toggleLoading();
            if (mostPopularTVShowsResponse != null) {
                totalAvailablePages = mostPopularTVShowsResponse.getPages();
                if (mostPopularTVShowsResponse.getTvShows() != null) {
                    int oldCount = tvShows.size();
                    tvShows.addAll(mostPopularTVShowsResponse.getTvShows());
                    tvShowAdapter.notifyItemRangeInserted(oldCount, tvShows.size());
                }
            }
        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            if (viewBinding.getIsLoading() != null && viewBinding.getIsLoading()) {
                viewBinding.setIsLoading(false);
            } else {
                viewBinding.setIsLoading(true);
            }
        } else {
            if (viewBinding.getIsLoadingMore() != null && viewBinding.getIsLoadingMore()) {
                viewBinding.setIsLoadingMore(false);
            } else {
                viewBinding.setIsLoadingMore(true);
            }
        }
    }

/*
    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Bundle bundle = new Bundle();
        bundle.putInt("id_tvShows", tvShow.getId());
        bundle.putString("name_tvShows", tvShow.getName());
        bundle.putString("startDate_tvShows", tvShow.getStartDate());
        bundle.putString("country_tvShows", tvShow.getCountry());
        bundle.putString("network_tvShows", tvShow.getNetwork());
        bundle.putString("status_tvShows", tvShow.getStatus());

        Log.d("test", String.valueOf(tvShow.getId()));

        findNavController().navigate(R.id.actionMostPopularToTVShowDetails, bundle);
    }
*/
}
