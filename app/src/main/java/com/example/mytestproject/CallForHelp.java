package com.example.mytestproject;

import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class CallForHelp {

    int[] nums = new int[]{0,1,2,3};

    public  void shuffle(){

        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            int temp = nums[i];
            int rand =  random.nextInt(4);
            nums[i] = nums[rand];
            nums[rand] = temp;

        }

    }

    public int[] removeQuestions(int correctAnswerIndex){
        shuffle();
        int[]x = new int[2];
        int place = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != correctAnswerIndex) {
                x[place] = nums[i];
                place++;
            }
            if(place== 2){
                return x;
            }
        }

        return x;
    }

    public String friendHelp(int correctAnswer){
        Random random = new Random();
        int rand =  random.nextInt(4);
        if(rand==0){
            int x =  random.nextInt(100);
            if(x>10){
                return "im positive that the answer is number "+(correctAnswer+1);
            }else{
                return "im positive that the answer is number "+random.nextInt(4);
            }
        }

        if(rand==1){
            int x =  random.nextInt(100);
            if(x>30){
                return "i think that the answer is number "+(correctAnswer+1);
            }else{
                return "im positive that the answer is number "+random.nextInt(4);
            }
        }

        if(rand==2){
            int x =  random.nextInt(100);
            if(x>50){
                return "i herd this before but i don't quite remember my guess is  "+(correctAnswer+1);
            }else{
                return "im positive that the answer is number "+random.nextInt(4);
            }
        }

        return "i have absolutely no idea....";
    }
}
