package com.sunbeaminfo.imageapp.model;

import android.graphics.Bitmap;

public class Photo
{
    int id;
    String author;
    Bitmap image;

    public Photo(){

    }
    public Photo(int id, String author,Bitmap image) {
        this.id = id;
        this.author = author;
        this.image=image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
