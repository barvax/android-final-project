package com.example.mytestproject.repos;

import android.util.Log;

import com.example.mytestproject.listeners.CategoryResponseListener;
import com.example.mytestproject.listeners.QuestionResponseListener;
import com.example.mytestproject.models.CategoryResponse;
import com.example.mytestproject.models.QuestionResponse;
import com.example.mytestproject.retrofit.ITriviaApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TriviaRepository {

    ITriviaApiService retrofit;
    private  static TriviaRepository instance;

    private TriviaRepository(){
        retrofit = ITriviaApiService.create();
    }
    public synchronized static TriviaRepository getInstance(){

        if(instance==null){
            instance = new TriviaRepository();
        }
        return instance;
    }

    public void getCategories(CategoryResponseListener listener){
        Call<CategoryResponse> call = retrofit.getCategory();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
//
                listener.onCategoryArrive(response.body());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.d("--------", "onCategory Fail to Arrive: "+t.getMessage()+t.getCause());
            }
        });
    }

    public void getQuestions(QuestionResponseListener listener,String category,String difficulty){
        Call<List<QuestionResponse>> call = retrofit.getQuestions(7,category,difficulty);
        call.enqueue(new Callback<List<QuestionResponse>>() {
            @Override
            public void onResponse(Call<List<QuestionResponse>> call, Response<List<QuestionResponse>> response) {
                    listener.onQuestionArrive(response.body());
            }

            @Override
            public void onFailure(Call<List<QuestionResponse>> call, Throwable t) {
                Log.d("---------", "onFailure: ");
            }
        });
    }
}
