package com.williamnb.readlistenapp.base;

import androidx.navigation.NavController;

import java.io.IOException;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public interface BaseView {
    void initializeView();

    void initializeComponent();

    void initializeEvents();

    void initializeData();

    void initializeDestroyView();

    void hideBottomNavigationView(boolean isHidden);

    NavController findNavController();
}
