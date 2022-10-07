package com.williamnb.readlistenapp.ui.features.tvshows.tvshow_search;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.data.remote.models.TVShow;
import com.williamnb.readlistenapp.data.remote.models.responses.TVShowsResponse;
import com.williamnb.readlistenapp.databinding.FragmentTvShowsSearchingBinding;
import com.williamnb.readlistenapp.ui.features.tvshows.most_popular_tvshows.TVShowAdapter;
import com.williamnb.readlistenapp.utilities.callback.TVShowsCallBack;

import java.util.ArrayList;
import java.util.List;

public class TvShowsSearchingFragment extends BaseFragment<FragmentTvShowsSearchingBinding, TvShowsSearchingViewModel> implements TVShowsCallBack {

    private TVShowAdapter adapter;
    private List<TVShow> tvShowList = new ArrayList<>();
    private int currentPage = 1;

    @Override
    public TvShowsSearchingViewModel createViewModel() {
        return new ViewModelProvider(this).get(TvShowsSearchingViewModel.class);
    }

    @Override
    public FragmentTvShowsSearchingBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentTvShowsSearchingBinding.inflate(inflater, container, false);
    }

    @Override
    public void initializeView() {
        viewBinding.edtSearch.setText("");
        viewBinding.deleteText.setImageResource(R.drawable.ic_search);
        viewBinding.rcv.setVisibility(View.VISIBLE);
        viewBinding.tvSearchStatus.setVisibility(View.VISIBLE);
        viewBinding.tvSearchStatus.setText(String.format("%s", "Chưa nhập từ khoá"));
        viewBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void initializeComponent() {
        adapter = new TVShowAdapter(tvShowList, this);
    }

    @Override
    public void initializeEvents() {
        viewBinding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                final Observer<TVShowsResponse> observer = new Observer<TVShowsResponse>() {
                    @Override
                    public void onChanged(@NonNull TVShowsResponse tvShowsResponse) {
                        tvShowList = tvShowsResponse.getTvShows();
                        viewBinding.rcv.setAdapter(adapter);
                        viewBinding.rcv.setVisibility(View.VISIBLE);
                        viewBinding.deleteText.setVisibility(View.VISIBLE);
//                        adapter.notifyItemRangeChanged(0, tvShowList.size());
                    }
                };
                if (!editable.toString().isEmpty()) {
                    viewModel.getTVShowsSearchingList(editable.toString(), currentPage).observe(TvShowsSearchingFragment.this, observer);
                    viewBinding.deleteText.setImageResource(R.drawable.ic_delete_book);
                } else {
                    viewBinding.deleteText.setImageResource(R.drawable.ic_search);
                }
            }
        });

        viewBinding.deleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!viewBinding.edtSearch.getText().toString().trim().isEmpty()) {
                    viewBinding.edtSearch.setText("");
                }
            }
        });
    }

    @Override
    public void initializeData() {
    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {

    }
}