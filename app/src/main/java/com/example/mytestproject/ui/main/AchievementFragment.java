package com.example.mytestproject.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mytestproject.DataManager;
import com.example.mytestproject.R;


public class AchievementFragment extends Fragment {


    Button btnBack;
    TextView tvCorrectAnswers;
    public AchievementFragment() {
        // Required empty public constructor
    }


    public static AchievementFragment newInstance() {
        AchievementFragment fragment = new AchievementFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_achievement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCorrectAnswers = view.findViewById(R.id.tv_correct_answers);
        btnBack = view.findViewById(R.id.btn_achievment_back);
        btnBack.setOnClickListener(view1 -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        });

        getAchievements();
    }

    public void getAchievements(){

        SharedPreferences pref  = getActivity().getSharedPreferences("achievements" , Context.MODE_PRIVATE);
       int correctAns =  pref.getInt("correctAnswersInt",0);
        tvCorrectAnswers.setText(String.valueOf(correctAns) ) ;
    }
}