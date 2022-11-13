package com.example.mytestproject;

public class DataManager {

    private static DataManager instance;
    private  String difficulty;
    private  String category;
    private int correctAnswers;
    private  int correctAnswersPerSession;
    private  int level = 1;
    private  int nextLevel=100;
    private int exp;


    public void setExpLevel(){
        nextLevel=level*100;
        if(exp>nextLevel){
           setExp(exp-=nextLevel);
            level++;
            nextLevel=level*100;

        }
    }
    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(int nextLevel) {
        this.nextLevel = nextLevel;
    }

    public int getCorrectAnswersPerSession() {
        return correctAnswersPerSession;
    }

    public void setCorrectAnswersPerSession(int correctAnswersPerSession) {
        this.correctAnswersPerSession = correctAnswersPerSession;
    }

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
