package com.example.mytestproject.ui.main;

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
import android.widget.Toast;

import com.example.mytestproject.DataManager;
import com.example.mytestproject.Images;
import com.example.mytestproject.R;


public class SummaryFragment extends Fragment {

    ImageView categoryImage;
    ImageView star1;
    ImageView star2;
    ImageView star3;
    TextView correctAnswers;
    DataManager dataManager;
    TextView homeBtn;
    TextView highScore;
    TextView outOf;
    TextView level;
    ProgressBar progressBar;



    public SummaryFragment() {
        // Required empty public constructor
    }

    public static SummaryFragment newInstance() {
        return new SummaryFragment();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        dataManager = DataManager.getDataManager();
        categoryImage.setImageResource(Images.categoryImages[dataManager.getCategoryIndex()]);
        correctAnswers.setText(String.valueOf(dataManager.getCorrectAnswersPerSession()));
        Images.setStars(star1,star2,star3,dataManager);
        setBar();
        highScore.setText(String.valueOf(dataManager.getHighScorePerCategory()[dataManager.getCategoryIndex()]));
        homeBtn.setOnClickListener(view1 -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        });

    }

    public void setBar(){
        dataManager  = DataManager.getDataManager();
        outOf.setText(String.valueOf(dataManager.getExp())+"/"+String.valueOf(dataManager.getNextLevel()));
        level.setText("level: "+((dataManager.getLevel())));
        progressBar.setMax(dataManager.getNextLevel());
        progressBar.setProgress(dataManager.getExp());
    }

    public void findViews(View view){
        categoryImage = view.findViewById(R.id.category_img_summary);
        star1 = view.findViewById(R.id.star1_summary);
        star2 = view.findViewById(R.id.star2_summary);
        star3 = view.findViewById(R.id.star3_summary);
        correctAnswers = view.findViewById(R.id.tv_correct_int);
        homeBtn = view.findViewById(R.id.home_tv);
        highScore = view.findViewById(R.id.tv_high_score_int);
        outOf = view.findViewById(R.id.level_tv_out_of_summary);
        progressBar=view.findViewById(R.id.progressBar3);
        level = view.findViewById(R.id.level_tv_summary);

    }


}