package com.example.mytestproject.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mytestproject.DataManager;
import com.example.mytestproject.R;


public class DifficultyFragment extends Fragment {

    private QuestionViewModel mViewModel;
    Button btn;
    TextView tvEasy;
    TextView tvMedium;
    TextView tvHard;
    public DifficultyFragment() {
        // Required empty public constructor
    }


    public static DifficultyFragment newInstance() {
        return new DifficultyFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_difficulty, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataManager data = DataManager.getDataManager();
        btn = view.findViewById(R.id.go_to_question_fragment_btn);
        btn.setEnabled(false);
        tvEasy = view.findViewById(R.id.tv_easy);
        tvMedium = view.findViewById(R.id.tv_mediun);
        tvHard  = view.findViewById(R.id.tv_hard);
        tvEasy.setOnClickListener(view1 -> {
            data.setDifficulty("easy");
            btn.setEnabled(true);
        });
        tvMedium.setOnClickListener(view1 -> {
            data.setDifficulty("medium");
            btn.setEnabled(true);
        });
        tvHard.setOnClickListener(view1 -> {
            data.setDifficulty("hard");
            btn.setEnabled(true);
        });
        btn.setOnClickListener(view1 -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, QuestionFragment.newInstance())
                    .commitNow();
        });
    }
}