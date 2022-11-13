package com.example.mytestproject;

import android.widget.ImageView;

public class Images {

    public static int[] categoryImages = {
            R.drawable.shome_sub_icon_ranking,
            R.drawable.btn_ad_icon,
            R.drawable.art,
            R.drawable.food_drink,
            R.drawable.language_small_eng,
            R.drawable.history,
            R.drawable.general_knolage,
            R.drawable.music,
            R.drawable.science,
            R.drawable.society,
            R.drawable.sports
    };

    public static int grayStarImage= R.drawable.gray_star;
    public static int fullStarImage= R.drawable.full_star;

    public static void setStars(ImageView first, ImageView sec , ImageView third,DataManager dataManager){
        first.setImageResource(Images.fullStarImage);
        sec.setImageResource(Images.grayStarImage);
        third.setImageResource(Images.grayStarImage);
        if(dataManager.getDifficulty()=="medium"){
            first.setImageResource(Images.fullStarImage);
            sec.setImageResource(Images.fullStarImage);
        }else if(dataManager.getDifficulty()=="hard"){
            first.setImageResource(Images.fullStarImage);
            sec.setImageResource(Images.fullStarImage);
            third.setImageResource(Images.fullStarImage);
        }
    }


}
