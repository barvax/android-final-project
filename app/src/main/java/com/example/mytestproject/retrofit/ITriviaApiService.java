package com.example.mytestproject.retrofit;

import com.example.mytestproject.models.CategoryResponse;
import com.example.mytestproject.models.QuestionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ITriviaApiService {
    @GET("categories")
    Call<CategoryResponse> getCategory();


//    @GET("questions?limit=5&region=IL")
    @GET("questions?region=IL")
    Call<List<QuestionResponse>> getQuestions(@Query("limit") int numOfQuestions,@Query("categories")String category,@Query("s") String difficulty );

     static ITriviaApiService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://the-trivia-api.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
      return  retrofit.create(ITriviaApiService.class);


    }


}
