package com.example.mytestproject.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytestproject.R;
import com.example.mytestproject.adapter.CategoriesAdapter;

import java.util.ArrayList;


public class SecondFragment extends Fragment {

    private MainViewModel mViewModel;
    ArrayList<String> categories;
    public SecondFragment() {

    }


    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            setCategories(view);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void setCategories(View view){
            mViewModel.getCategory().observe(getViewLifecycleOwner(),categoryResponse -> {
            categories = new ArrayList<>();
            categories.add("all categories");
            categories.add(categoryResponse.getFilmTV().get(0));
            categories.add(categoryResponse.getArtsLiterature().get(0));
            categories.add(categoryResponse.getFoodDrink().get(0));
            categories.add(categoryResponse.getGeography().get(0));
            categories.add(categoryResponse.getHistory().get(0));
            categories.add(categoryResponse.getGeneralKnowledge().get(0));
            categories.add(categoryResponse.getMusic().get(0));
            categories.add(categoryResponse.getScience().get(0));
            categories.add(categoryResponse.getSocietyCulture().get(0));
            categories.add(categoryResponse.getSportLeisure().get(0));




            RecyclerView recyclerView = view.findViewById(R.id.category_recycle_view);
            CategoriesAdapter adapter = new CategoriesAdapter(categories);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        });
    }
}