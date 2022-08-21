package com.williamnb.readlistenapp.base;

import androidx.navigation.NavController;

public interface BaseView {
    void initializeView();

    void initializeComponent();

    void initializeEvents();

    void initializeData();

    void initializeDestroyView();

    void hideBottomNavigationView(boolean isHidden);

    NavController findNavController();
}
