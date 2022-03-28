package com.williamnb.readlistenapp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<VB extends ViewBinding, VM extends BaseViewModel>
        extends Fragment implements BaseView{
    protected VB viewBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
    }

    public abstract VB createViewBinding(LayoutInflater inflater, ViewGroup container);

    protected void onPostOnCreate() {
        // DO_NO_THING
    }

    protected abstract VB getActivityBinding();

    public NavController findNavController() {
        return Navigation.findNavController(viewBinding.getRoot());
    }
}
