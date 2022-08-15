package com.williamnb.readlistenapp.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.williamnb.readlistenapp.ui.features.main.MainActivity;

public abstract class BaseFragment<VB extends ViewBinding, VM extends BaseViewModel>
        extends Fragment implements BaseView {
    protected VB viewBinding;
    protected VM viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onPostOnCreate();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewBinding = createViewBinding(inflater, container);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
        initializeComponent();
        initializeEvents();
        initializeData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewBinding = null;
        initializeDestroyView();
        Log.d("Destroy", "Destroyed");
    }

    @Override
    public void initializeDestroyView(){

    }

    @Override
    public void hideBottomNavigationView(boolean isHidden){
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null)
            activity.hideBottomNavigationView(isHidden);
    }

    @Override
    public NavController findNavController() {
        return Navigation.findNavController(viewBinding.getRoot());
    }

    public abstract VB createViewBinding(LayoutInflater inflater, ViewGroup container);

    protected void onPostOnCreate() {
        viewModel = createViewModel();
    }

    public abstract VM createViewModel();
}
