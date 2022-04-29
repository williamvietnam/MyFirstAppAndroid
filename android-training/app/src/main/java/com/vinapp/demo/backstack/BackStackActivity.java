package com.vinapp.demo.backstack;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vinapp.demo.R;
import com.vinapp.demo.databinding.ActivityBackStackBinding;

public class BackStackActivity extends AppCompatActivity{

    private ActivityBackStackBinding binding;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBackStackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listener();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new OneFragment(), null);
        //fragmentTransaction.replace(R.id.container, new FragmentA(), null);
        fragmentTransaction.commit();
    }

    private void listener(){
        binding.btnAdd.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, new TwoFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        binding.btnReplace.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
            fragmentTransaction1.replace(R.id.container, new TwoFragment());
            fragmentTransaction1.addToBackStack(null);
            fragmentTransaction1.commit();
        });
    }
}
