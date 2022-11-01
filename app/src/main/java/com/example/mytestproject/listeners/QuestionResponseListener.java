package com.example.mytestproject.listeners;

import com.example.mytestproject.models.QuestionResponse;

import java.util.List;

public interface QuestionResponseListener {

    void onQuestionArrive(List<QuestionResponse> questions);
}
