package com.avinashiyer.allergent.utils;

/**
 * Created by avinashiyer on 2/25/17.
 */

public class AllergyObject {

    String name;
    int photo;

    public AllergyObject(String name, int photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
