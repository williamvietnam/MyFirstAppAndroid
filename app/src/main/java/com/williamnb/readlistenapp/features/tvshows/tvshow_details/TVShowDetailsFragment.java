package com.williamnb.readlistenapp.features.tvshows.tvshow_details;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentTVShowDetailsBinding;

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

    }

    @Override
    public void initializeEvents() {

    }

    @Override
    public void initializeData() {

    }


}
