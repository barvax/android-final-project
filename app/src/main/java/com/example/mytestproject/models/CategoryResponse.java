package com.example.mytestproject.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("Arts & Literature")
    private List<String> artsLiterature ;
    @SerializedName("Film & TV")
    private List<String>  filmTV;
    @SerializedName("Food & Drink")
    private List<String>  foodDrink;
    @SerializedName("General Knowledge")
    private List<String>  generalKnowledge;
    private List<String>  Geography;
    private List<String>  History;
    private List<String>  Music;
    private List<String>  Science;
    @SerializedName("Society & Culture")
    private List<String>  societyCulture;
    @SerializedName("Sport & Leisure")
    private List<String>  sportLeisure;

    public List<String> getArtsLiterature() {
        return artsLiterature;
    }

    public void setArtsLiterature(List<String> artsLiterature) {
        this.artsLiterature = artsLiterature;
    }

    public List<String> getFilmTV() {
        return filmTV;
    }

    public void setFilmTV(List<String> filmTV) {
        this.filmTV = filmTV;
    }

    public List<String> getFoodDrink() {
        return foodDrink;
    }

    public void setFoodDrink(List<String> foodDrink) {
        this.foodDrink = foodDrink;
    }

    public List<String> getGeneralKnowledge() {
        return generalKnowledge;
    }

    public void setGeneralKnowledge(List<String> generalKnowledge) {
        this.generalKnowledge = generalKnowledge;
    }

    public List<String> getGeography() {
        return Geography;
    }

    public void setGeography(List<String> geography) {
        Geography = geography;
    }

    public List<String> getHistory() {
        return History;
    }

    public void setHistory(List<String> history) {
        History = history;
    }

    public List<String> getMusic() {
        return Music;
    }

    public void setMusic(List<String> music) {
        Music = music;
    }

    public List<String> getScience() {
        return Science;
    }

    public void setScience(List<String> science) {
        Science = science;
    }

    public List<String> getSocietyCulture() {
        return societyCulture;
    }

    public void setSocietyCulture(List<String> societyCulture) {
        this.societyCulture = societyCulture;
    }

    public List<String> getSportLeisure() {
        return sportLeisure;
    }

    public void setSportLeisure(List<String> sportLeisure) {
        this.sportLeisure = sportLeisure;
    }
}
