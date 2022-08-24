package com.williamnb.readlistenapp.ui.features.media.songlist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentSongListBinding;
import com.williamnb.readlistenapp.ui.features.media.adapter.SongAdapter;
import com.williamnb.readlistenapp.utilities.Constants;
import com.williamnb.readlistenapp.utilities.callback.MediaCallBack;

public class SongListFragment extends BaseFragment<FragmentSongListBinding, SongListViewModel> implements MediaCallBack {

    private SongAdapter songAdapter;

    @Override
    public SongListViewModel createViewModel() {
        return new ViewModelProvider(this).get(SongListViewModel.class);
    }

    @Override
    public FragmentSongListBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentSongListBinding.inflate(inflater, container, false);
    }

    @Override
    public void initializeView() {
        this.songAdapter = new SongAdapter(this, viewModel.getSongList());
        this.viewBinding.rcv.setAdapter(songAdapter);
    }

    @Override
    public void initializeComponent() {
    }

    @Override
    public void initializeEvents() {
        viewBinding.btnClose.setOnClickListener(v -> {
            findNavController().popBackStack();
            Log.d(SongListFragment.class.getSimpleName(), "debug: close clicked");
        });
    }

    @Override
    public void initializeData() {
    }

    @Override
    public void onItemClicked(int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.SONG_POSITION_IN_SONG_LIST, position);
        args.putParcelableArrayList(Constants.SONG_LIST, viewModel.getSongList());
        findNavController().navigate(R.id.actionSongListToMediaMain, args);
    }
}