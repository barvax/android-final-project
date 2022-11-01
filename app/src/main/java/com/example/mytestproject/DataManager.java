package com.example.mytestproject;

public class DataManager {

    private static DataManager instance;
    private  String difficulty;
    private  String category;
    private int correctAnswers;
    private int[]highScorePerCategory = new int[20];
    private int categoryIndex;

    private DataManager() {

    }

    public int getCategoryIndex() {
        return categoryIndex;
    }

    public void setCategoryIndex(int categoryIndex) {
        this.categoryIndex = categoryIndex;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int[] getHighScorePerCategory() {
        return highScorePerCategory;
    }

    public void setHighScorePerCategory(int index, int highScorePerCategory) {
        this.highScorePerCategory[index] = highScorePerCategory;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static DataManager getDataManager(){
        if(instance==null){
            instance = new DataManager();
        }
        return  instance;
    }
}
