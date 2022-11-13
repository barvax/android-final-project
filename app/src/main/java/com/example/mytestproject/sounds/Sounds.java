package com.example.mytestproject.sounds;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

import com.example.mytestproject.R;

public class Sounds extends View {
    MediaPlayer bip;
    MediaPlayer error;
    MediaPlayer trueAnswer;
    MediaPlayer wrongAnswer;
    MediaPlayer clickBtn;

    public Sounds(Context context) {
        super(context);
        bip = MediaPlayer.create(getContext(),R.raw.time_left_sound);
        error = MediaPlayer.create(getContext(),R.raw.error_sound);
        trueAnswer = MediaPlayer.create(getContext(),R.raw.true_answer);
        wrongAnswer = MediaPlayer.create(getContext(),R.raw.wrong_answer);
        clickBtn = MediaPlayer.create(getContext(),R.raw.click_btn_sound);
    }


    public void playTimeLeftSound(){
       bip.start();
    }
    public void playErrorSound(){
       error.start();
    }
    public void playTrueAnswer(){
        trueAnswer.start();
    }
    public void playWrongAnswer(){
        wrongAnswer.start();
    }
    public void playClickBtn(){
        clickBtn.start();
    }


}
