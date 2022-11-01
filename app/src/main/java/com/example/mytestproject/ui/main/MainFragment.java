package com.example.mytestproject.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mytestproject.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    Button nextBtn;
    ImageView achievementBtn;
    private String chosenCategory;
    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mViewModel.getCategory().observe(getViewLifecycleOwner(),categoryResponse -> {
//
//
//        });

     nextBtn = view.findViewById(R.id.nextBtnFragment1);

       nextBtn.setOnClickListener(view1 ->{

           getParentFragmentManager().beginTransaction()
                   .replace(R.id.container, HomeFragment.newInstance())
                   .commitNow();
       } );

        achievementBtn = view.findViewById(R.id.btn_achievement);
        achievementBtn.setOnClickListener(view1 -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, AchievementFragment.newInstance())
                    .commitNow();
        });
    }


}