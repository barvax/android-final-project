package com.example.mytestproject.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mytestproject.listeners.CategoryResponseListener;

import com.example.mytestproject.models.CategoryResponse;

import com.example.mytestproject.repos.TriviaRepository;



public class MainViewModel extends ViewModel  {

    private  MutableLiveData<CategoryResponse> categoriesLiveData;

    public LiveData<CategoryResponse> getCategory(){
        if(categoriesLiveData==null){
            categoriesLiveData = new MutableLiveData<>();
        }

        TriviaRepository.getInstance().getCategories(new CategoryResponseListener() {

            @Override

            public void onCategoryArrive(CategoryResponse categories) {

                categoriesLiveData.postValue(categories);
            }
        });
        return  categoriesLiveData;
    }




}