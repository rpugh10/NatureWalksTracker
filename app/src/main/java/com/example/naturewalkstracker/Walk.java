package com.example.naturewalkstracker;

public class Walk {

    int id;

    String title;

    String location;

    int imageResId;

    boolean isSelected = false;

    public Walk(int id, String title, String location, int imageResId, boolean isSelected) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.imageResId = imageResId;
        this.isSelected = isSelected;
    }
}
