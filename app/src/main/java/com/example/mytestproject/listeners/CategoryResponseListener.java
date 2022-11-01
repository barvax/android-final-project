package com.example.mytestproject.listeners;

import com.example.mytestproject.models.CategoryResponse;

import java.util.List;

public interface CategoryResponseListener {
    void onCategoryArrive(CategoryResponse categories);
}
