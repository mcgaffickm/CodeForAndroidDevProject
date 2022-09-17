package com.example.secretsofthecursedcastle;

import java.io.Serializable;

public class SelectedOptions implements Serializable {
    boolean hints = false;
    boolean infiniteLives = false;

    boolean bestEnding = true;

    boolean lightSensor = false;
    boolean phone = false;
    boolean bestPath = false;
    String phoneText = "XXXXXXXXXX";

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
