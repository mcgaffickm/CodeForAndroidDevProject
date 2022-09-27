package com.example.secretsofthecursedcastle;

import java.io.Serializable;

//This is my "transit" class that carries the options from option page to the main activity
//Must be serializable to transfer with intents
public class SelectedOptions implements Serializable {
    //Declaration of variables
    boolean hints;
    boolean infiniteLives;

    boolean bestEnding;

    boolean lightSensor;
    boolean phone;
    boolean bestPath;
    String phoneText;

    //Constructor that gives default values
    public SelectedOptions()
    {
        hints = false;
        infiniteLives = false;

        bestEnding = true;

        lightSensor = true;
        phone = false;
        bestPath = false;
        phoneText = "XXXXXXXXXX";
    }

    //Getters and setters for everything
    public boolean isHints() {
        return hints;
    }

    public void setHints(boolean hints) {
        this.hints = hints;
    }

    public boolean isInfiniteLives() {
        return infiniteLives;
    }

    public void setInfiniteLives(boolean infiniteLives) {
        this.infiniteLives = infiniteLives;
    }

    public boolean isBestEnding() {
        return bestEnding;
    }

    public void setBestEnding(boolean bestEnding) {
        this.bestEnding = bestEnding;
    }

    public boolean isLightSensor() {
        return lightSensor;
    }

    public void setLightSensor(boolean lightSensor) {
        this.lightSensor = lightSensor;
    }

    public boolean isPhone() {
        return phone;
    }

    public void setPhone(boolean phone) {
        this.phone = phone;
    }

    public boolean isBestPath() {
        return bestPath;
    }

    public void setBestPath(boolean bestPath) {
        this.bestPath = bestPath;
    }

    public String getPhoneText() {
        return phoneText;
    }

    public void setPhoneText(String phoneText) {
        this.phoneText = phoneText;
    }
}
