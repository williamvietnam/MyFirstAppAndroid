package com.williamnb.readlistenapp.features.media;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentMediaMainBinding;

public class MediaMainFragment extends BaseFragment<FragmentMediaMainBinding, MediaMainViewModel> {
    @Override
    public FragmentMediaMainBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentMediaMainBinding.inflate(inflater, container, false);
    }

    @Override
    public MediaMainViewModel createViewModel() {
        return new ViewModelProvider(this).get(MediaMainViewModel.class);
    }

    @Override
    public void initializeView() {

    }

    @Override
    public void initializeComponent() {

    }

    @Override
    public void initializeEvents() {
        viewBinding.imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSongList();
            }
        });
    }

    @Override
    public void initializeData() {
        //TODO
    }

    /**
     * SongList: is one Dialog 2/3 screen from right to left show song list
     * */
    private void openSongList() {

    }

}
