package com.example.mytestproject.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mytestproject.listeners.QuestionResponseListener;
import com.example.mytestproject.models.QuestionResponse;
import com.example.mytestproject.repos.TriviaRepository;

import java.util.List;

public class QuestionViewModel extends ViewModel {
    private  MutableLiveData<List<QuestionResponse>> questionsLiveData;


    public LiveData<List<QuestionResponse>> getQuestions(String categories, String difficulty){

        if(questionsLiveData==null){
            questionsLiveData = new MutableLiveData<>();
        }
        TriviaRepository.getInstance().getQuestions(new QuestionResponseListener() {
            @Override
            public void onQuestionArrive(List<QuestionResponse> questions) {

                questionsLiveData.postValue(questions);
            }
        },categories,difficulty);

        return questionsLiveData;
    }
}