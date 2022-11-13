package com.example.mytestproject.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytestproject.CallForHelp;
import com.example.mytestproject.DataManager;
import com.example.mytestproject.Images;
import com.example.mytestproject.R;
import com.example.mytestproject.models.QuestionResponse;
import com.example.mytestproject.sounds.Sounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionFragment extends Fragment {

    private QuestionViewModel mViewModel;
    private List<QuestionResponse> questions;
    String[] answers;
    TextView[] answersTextView;
    CountDownTimer myTimer;
    DataManager dataManager;
    private int questionNum = 0;
    TextView questionNumber;
    int correctAnswerIndex;
    TextView category;
    TextView difficulty;
    TextView question;
    TextView answer1;
    TextView answer2;
    TextView answer3;
    TextView answer4;
    TextView timer;
    Button nextQuestionBtn;
    ImageView endSessionBtn;
    Button helpBtn;
    Button friendBtn;
    boolean isFriend =true;
    boolean isHelp =true;
    ImageView categoryImg;
    Sounds sounds;
    ImageView firstStar;
    ImageView secondStar;
    ImageView thirdStar;
    TextView correctAnswerTv;
    int correctAnswers;



    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViews(view);
        nextQuestionBtn.setEnabled(false);
        mViewModel.getQuestions(dataManager.getCategory(),dataManager.getDifficulty()).observe(getViewLifecycleOwner(), questionResponses -> {
            questions = new ArrayList<>();
            for (QuestionResponse item : questionResponses) {
                questions.add(item);
            }
            initSession();
        });
    }

    public void initSession(){

        category.setText("Category: "+dataManager.getCategory());
        difficulty.setText("Difficulty: "+dataManager.getDifficulty());
        categoryImg.setImageResource(Images.categoryImages[dataManager.getCategoryIndex()]);
        correctAnswerTv.setText(String.valueOf(correctAnswers));
        setAnswers();
        shuffleAnswers();
        showCurrentQuestion();
        onAnswerClicked();
        sounds = new Sounds(getContext());
        myTimer();
        helpers();
        nextQuestionBtn.setOnClickListener(view -> {
            sounds.playClickBtn();
            nextQuestion();
        });
        endSessionBtn.setOnClickListener(view -> {
            sounds.playClickBtn();
            myTimer.cancel();
            endSession();
        });

    }

    public void helpers(){
        helpBtn.setOnClickListener(view -> {
            shootHelpAnswers();
            isHelp=false;
            helpBtn.setEnabled(false);
        });
        friendBtn.setOnClickListener(view -> {
            CallForHelp help = new CallForHelp();
            AlertDialog.Builder z = new AlertDialog.Builder(getContext());
            z.setTitle("Hi").setMessage( help.friendHelp(getCorrectAnswer()))
                    .setNegativeButton("Exit",((dialogInterface, i) -> {
                    }));
            AlertDialog dialog = z.create();
            dialog.show();
            isFriend = false;
            friendBtn.setEnabled(false);
        });
    }
    public void setAnswers(){
        answers = new String[]{
                questions.get(questionNum).getCorrectAnswer(),
                questions.get(questionNum).getIncorrectAnswers().get(0),
                questions.get(questionNum).getIncorrectAnswers().get(1),
                questions.get(questionNum).getIncorrectAnswers().get(2)};
    }
    public void shuffleAnswers(){

        for (int i = 0; i < answers.length; i++) {
            Random random = new Random();
            String temp = answers[i];
            int rand =  random.nextInt(answers.length);
            answers[i] = answers[rand];
            answers[rand] = temp;
        }
    }

    public int getCorrectAnswer(){
        for (int i = 0; i < answers.length; i++) {
            if(answers[i]==  questions.get(questionNum).getCorrectAnswer()){
                correctAnswerIndex=i;
                return i;
            }
        }
        return 0;
    }



    public void showCurrentQuestion(){

        questionNumber.setText(questionNum+1+" question out of "+questions.size()+" questions");
        question.setText(questions.get(questionNum).getQuestion());
        Images.setStars(firstStar,secondStar,thirdStar,dataManager);
        answer1.setText(answers[0]);
        answer2.setText(answers[1]);
        answer3.setText(answers[2]);
        answer4.setText(answers[3]);
    }



    public void shootHelpAnswers(){
        CallForHelp help = new CallForHelp();
        int[]z = help.removeQuestions(getCorrectAnswer());
        for (int i = 0; i < 2; i++) {
            answersTextView[z[i]].setTextColor(Color.parseColor("#e28743"));
        }

    }

    public boolean isEndSession(){
        if(questionNum<questions.size()-1){
            return true;
        }
        return false;
    }
    public void startEnsSessionCoroutine(){
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {}
            public void onFinish() {
                dataManager.setCorrectAnswersPerSession(correctAnswers);
                endSession();
            }
        }.start();
    }

    private void onAnswerClicked(){

        answersTextView = new TextView []{answer1, answer2, answer3, answer4};
        for (int i = 0; i < answers.length; i++) {
            TextView answersView = answersTextView[i];
            answersTextView[i].setOnClickListener(view -> {
                if(answersView.getText() ==questions.get (questionNum).getCorrectAnswer()){
                    correctAnswer();
                }else {
                    wrongAnswer(answersView);
                }
                answersTextView[getCorrectAnswer()].setTextColor(Color.parseColor("#38b01e"));
                friendBtn.setEnabled(false);
                helpBtn.setEnabled(false);
                if(isEndSession()){
                    nextQuestionBtn.setEnabled(true);
                }else {
                    startEnsSessionCoroutine();
                }
            });
        }
    }

    public void correctAnswer(){
        correctAnswers++;
        correctAnswerTv.setText(String.valueOf(correctAnswers));
        sounds.playTrueAnswer();
        if(dataManager.getCorrectAnswers()<correctAnswers){
            dataManager.setCorrectAnswers(correctAnswers);
        }
        if(dataManager.getHighScorePerCategory()[dataManager.getCategoryIndex()]<correctAnswers){
            dataManager.setHighScorePerCategory(dataManager.getCategoryIndex(),correctAnswers);
        }
        disableAnswers();
    }

    public void wrongAnswer(TextView textView){
        textView.setTextColor(Color.parseColor("#e28743"));
        sounds.playWrongAnswer();
        disableAnswers();
    }
    private void getViews(View view) {
        dataManager = DataManager.getDataManager();
        question  = view.findViewById(R.id.tv_the_question);
        questionNumber = view.findViewById(R.id.tv_question_num);
        category = view.findViewById(R.id.tv_question_category);
        difficulty = view.findViewById(R.id.tv_difficulty);
        answer1 = view.findViewById(R.id.tv_answer1);
        answer2 = view.findViewById(R.id.tv_answer2);
        answer3 = view.findViewById(R.id.tv_answer3);
        answer4 = view.findViewById(R.id.tv_answer4);
        timer = view.findViewById(R.id.tv_timer);
        nextQuestionBtn = view.findViewById(R.id.btn_next_question);
        endSessionBtn = view.findViewById(R.id.end_session_btn);
        helpBtn = view.findViewById(R.id.help_btn);
        friendBtn = view.findViewById(R.id.friend_btn);
        categoryImg = view.findViewById(R.id.categoty_image);
        firstStar = view.findViewById(R.id.first_star);
        secondStar = view.findViewById(R.id.second_star);
        thirdStar = view.findViewById(R.id.third_star);
        correctAnswerTv = view.findViewById(R.id.correct_answer_tv);
    }

    public void disableAnswers(){
        for (int i = 0; i < answersTextView.length; i++) {
            answersTextView[i].setEnabled(false);
        }
        myTimer.cancel();
    }

    public void resetAnswersColor(){
        for (int i = 0; i < answers.length; i++) {
            answersTextView[i].setTextColor(Color.parseColor("#ffffff"));
            answersTextView[i].setEnabled(true);
        }
    }

    public void nextQuestion(){
        questionNum++;
        friendBtn.setEnabled(isFriend);
        helpBtn.setEnabled(isHelp);
        setAnswers();
        shuffleAnswers();
        showCurrentQuestion();
        resetAnswersColor();
        nextQuestionBtn.setEnabled(false);
        myTimer();
    }

    public  void myTimer(){
        timer.setTextColor(Color.parseColor("#ffffff"));
        if(myTimer!=null){
            myTimer.cancel();
        }
        myTimer =  new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf (millisUntilFinished / 1000)+" sec left");
                if(millisUntilFinished /1000<6&millisUntilFinished/1000>0){
                    timer.setTextColor(Color.parseColor("#ff0000"));
                    if(millisUntilFinished/1000%millisUntilFinished/1000==0){
                        sounds.playTimeLeftSound();
                    }
                }else if(millisUntilFinished /1000==0){
                    sounds.playErrorSound();
                }
            }
            public void onFinish() {
                timer.setText("x");
                nextQuestion();
            }
        }.start();
    }


    public void endSession(){

        DataManager data = DataManager.getDataManager();
        SharedPreferences pref  = getActivity().getSharedPreferences("achievements" , Context.MODE_PRIVATE);
        pref.edit().putInt("highScorePerCategory"+data.getCategoryIndex(),data.getHighScorePerCategory()[data.getCategoryIndex()]).apply();
        pref.edit().putInt("correctAnswersInt",data.getCorrectAnswers()).apply();
        dataManager.setExp(data.getExp()+ correctAnswers* mViewModel.difficultyLevel());
        dataManager.setExpLevel();
        pref.edit().putInt("exp",dataManager.getExp()).apply();
        pref.edit().putInt("level",dataManager.getLevel()).apply();
        pref.edit().putInt("nextLevel",dataManager.getNextLevel()).apply();

        getParentFragmentManager().beginTransaction()
                .replace(R.id.container, SummaryFragment.newInstance())
                .commitNow();
    }
}