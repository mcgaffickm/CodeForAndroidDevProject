package com.example.secretsofthecursedcastle;

//Model class for the armory
public class Tool {
    //Declaration of variables
    String name, description;
    int imageID;
    boolean takeItem;

    //Constructor
    public Tool(String name, String description, int imageID, boolean takeItem) {
        this.name = name;
        this.description = description;
        this.imageID = imageID;
        this.takeItem = takeItem;
    }

    //Getter and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public boolean isTakeItem() {
        return takeItem;
    }

    public void setTakeItem(boolean takeItem) {
        this.takeItem = takeItem;
    }
}
