package com.williamnb.readlistenapp.ui.features.media;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;
import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.data.local.models.Song;
import com.williamnb.readlistenapp.databinding.FragmentMediaMainBinding;
import com.williamnb.readlistenapp.utilities.Constants;

public class MediaMainFragment extends BaseFragment<FragmentMediaMainBinding, MediaMainViewModel> {

    @Override
    public MediaMainViewModel createViewModel() {
        return new ViewModelProvider(this).get(MediaMainViewModel.class);
    }

    @Override
    public FragmentMediaMainBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentMediaMainBinding.inflate(inflater, container, false);
    }

    @Override
    public void initializeView() {
        if (getArguments() != null) {
            viewModel.setSongList(getArguments().getParcelableArrayList(Constants.SONG_LIST));
            viewModel.setSongPosition(getArguments().getInt(Constants.SONG_POSITION_IN_SONG_LIST));
        }
    }

    @Override
    public void initializeComponent() {
        updateData(viewModel.getSongPosition());
    }

    @Override
    public void initializeEvents() {
        viewBinding.imageMenu.setOnClickListener(v -> {
            Log.d(MediaMainFragment.class.getSimpleName(), "debug: open song list screen");
            findNavController().navigate(R.id.actionMediaToSongList);
        });

        viewBinding.imageSearch.setOnClickListener(v -> {
            Log.d(MediaMainFragment.class.getSimpleName(), "debug: open search screen");
            Toast.makeText(requireContext(), "Dang phat trien", Toast.LENGTH_SHORT).show();
        });

        viewBinding.imagePlayPause.setOnClickListener(v -> {
            if (viewModel.getMediaPlayer().isPlaying()) {
                viewModel.getMediaPlayer().stop();
                viewModel.getMediaPlayer().reset();
                viewModel.getMediaPlayer().release();
                Log.d(MediaMainFragment.class.getSimpleName(), "Audio has been paused");
            } else {
                Log.d(MediaMainFragment.class.getSimpleName(), "Calling method to play audio");
                viewModel.playAudio(viewModel.getSongPosition());
            }
        });

        viewBinding.imageNext.setOnClickListener(v -> {
            viewModel.setSongPosition(viewModel.getSongPosition() + 1);
            viewModel.playAudio(viewModel.getSongPosition());
            updateData(viewModel.getSongPosition());
        });

        viewBinding.imagePrevious.setOnClickListener(v -> {
            viewModel.setSongPosition(viewModel.getSongPosition() - 1);
            viewModel.playAudio(viewModel.getSongPosition());
            updateData(viewModel.getSongPosition());
        });
    }

    @Override
    public void initializeData() {
        //TODO
    }

    private void updateData(int position) {
        Song song = viewModel.getSongList().get(position);
        Picasso.get().load(song.getThumbnail()).error(R.drawable.ic_music).into(viewBinding.imvThumbnail);
        viewBinding.textArtist.setText(song.getArtist());
        viewBinding.textTitle.setText(song.getSongName());
        if (viewModel.getMediaPlayer().isPlaying()) {
            viewBinding.tvSongStatus.setText("Đang phát nhạc");
        } else {
            viewBinding.tvSongStatus.setText("Chưa phát nhạc");
        }
    }
}