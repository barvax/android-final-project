package com.example.mytestproject.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mytestproject.DataManager;
import com.example.mytestproject.R;
import com.example.mytestproject.sounds.Sounds;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    Button nextBtn;
    ImageView achievementBtn;
    private String chosenCategory;
    TextView level;
    TextView outOf;
    DataManager dataManager;
    ProgressBar progressBar;
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
        Sounds sounds = new Sounds(getContext());
        dataManager  = DataManager.getDataManager();
        outOf = view.findViewById(R.id.level_tv_out_of);
        outOf.setText(String.valueOf(dataManager.getExp())+"/"+String.valueOf(dataManager.getNextLevel()));
        nextBtn = view.findViewById(R.id.nextBtnFragment1);
        progressBar=view.findViewById(R.id.progressBar1);
        level = view.findViewById(R.id.level_tv2);
        level.setText("level: "+((dataManager.getLevel())));
        progressBar.setMax(dataManager.getNextLevel());
        progressBar.setProgress(dataManager.getExp());


        nextBtn.setOnClickListener(view1 ->{
            sounds.playClickBtn();
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, SecondFragment.newInstance())
                    .commitNow();
        } );

        achievementBtn = view.findViewById(R.id.btn_about);
        achievementBtn.setOnClickListener(view1 -> {
            sounds.playClickBtn();
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, AboutFragment.newInstance())
                    .commitNow();
        });
    }


}