package com.example.mytestproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mytestproject.ui.main.MainFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
        initDataManager();

    }
    public void initDataManager(){

        DataManager data = DataManager.getDataManager();
        SharedPreferences pref  = getSharedPreferences("achievements" , Context.MODE_PRIVATE);
        int correctAns =  pref.getInt("correctAnswersInt",0);
        data.setCorrectAnswers(correctAns);

        for (int i = 0; i < data.getHighScorePerCategory().length; i++) {
            int correctPerCategory = pref.getInt("highScorePerCategory"+i,0);
            data.setHighScorePerCategory(i,correctPerCategory);
        }
        data.setExp(pref.getInt("exp",0));
        data.setLevel(pref.getInt("level",0));
        data.setNextLevel(pref.getInt("nextLevel",0));
    }




}