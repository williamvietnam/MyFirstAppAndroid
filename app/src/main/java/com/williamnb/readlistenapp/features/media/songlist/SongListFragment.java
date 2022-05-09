package com.williamnb.readlistenapp.features.media.songlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentSongListBinding;

public class SongListFragment extends BaseFragment<FragmentSongListBinding, SongListViewModel> {

    @Override
    public FragmentSongListBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentSongListBinding.inflate(inflater, container, false);
    }

    @Override
    public SongListViewModel createViewModel() {
        return new ViewModelProvider(this).get(SongListViewModel.class);
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
