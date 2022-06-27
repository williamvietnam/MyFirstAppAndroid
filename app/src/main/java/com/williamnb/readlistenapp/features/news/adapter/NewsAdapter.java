package com.williamnb.readlistenapp.features.news.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.williamnb.readlistenapp.features.news.tab.Tab1Fragment;
import com.williamnb.readlistenapp.features.news.tab.Tab2Fragment;
import com.williamnb.readlistenapp.features.news.tab.Tab3Fragment;

public class NewsAdapter extends FragmentStateAdapter {

    public NewsAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Tab1Fragment();
            case 1:
                return new Tab2Fragment();
            case 2:
                return new Tab3Fragment();
            default:
                return new Tab1Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
