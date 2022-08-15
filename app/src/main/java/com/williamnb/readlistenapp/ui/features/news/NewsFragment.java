package com.williamnb.readlistenapp.ui.features.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentNewsBinding;
import com.williamnb.readlistenapp.ui.features.news.adapter.NewsAdapter;

public class NewsFragment extends BaseFragment<FragmentNewsBinding, NewsViewModel> {

    @Override
    public FragmentNewsBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentNewsBinding.inflate(inflater, container, false);
    }

    @Override
    public NewsViewModel createViewModel() {
        return new ViewModelProvider(this).get(NewsViewModel.class);
    }

    @Override
    public void initializeView() {
        hideBottomNavigationView(true);
        NewsAdapter newsAdapter = new NewsAdapter(this);
        viewBinding.viewPager.setAdapter(newsAdapter);
    }

    @Override
    public void initializeComponent() {

    }

    @Override
    public void initializeEvents() {
        new TabLayoutMediator(viewBinding.tabLayout, viewBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("DanTri");
                        break;
                    case 1:
                        tab.setText("VnExpress");
                        break;
                    case 2:
                        tab.setText("Youtube");
                        break;
                }
            }
        }).attach();

        viewBinding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController().popBackStack();
            }
        });
    }

    @Override
    public void initializeData() {

    }
}
