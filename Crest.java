package com.example.secretsofthecursedcastle;

//A model class to hold stuff for the kings room
public class Crest {
    //Variables
    String name, date, description;
    int imageId, correctAnswer;

    //Constructor
    public Crest(String name, String date, String description, int imageId, int correctAnswer) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.imageId = imageId;
        this.correctAnswer = correctAnswer;
    }

    //Variables, getter and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
