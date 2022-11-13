package com.example.mytestproject.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mytestproject.R;
import com.example.mytestproject.sounds.Sounds;


public class AboutFragment extends Fragment {


    Button btnBack;
    TextView tvAbout;
    TextView tvEmailMe;
    ImageView emailMe;

    public AboutFragment() {
        // Required empty public constructor
    }


    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Sounds sounds = new Sounds(getContext());
        getViews(view);
        tvAbout.setText("App Created By Ron Nisenblat");
        tvEmailMe.setText("Email me Here to: Barvax35@gmail.com");

        btnBack.setOnClickListener(view1 -> {
            sounds.playClickBtn();
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        });
        emailMe.setOnClickListener(view1 -> {
            Intent mailIntent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:?subject=new message from trivia user"  + "&to=" + "Barvax35@gmail.com");
            mailIntent.setData(data);
            startActivity(Intent.createChooser(mailIntent, "Send mail..."));
        });


    }

    private void getViews(View view) {
        emailMe = view.findViewById(R.id.email_me);
        tvAbout = view.findViewById(R.id.tv_about);
        tvEmailMe = view.findViewById(R.id.tv_email);
        btnBack = view.findViewById(R.id.btn_achievment_back);
    }


}