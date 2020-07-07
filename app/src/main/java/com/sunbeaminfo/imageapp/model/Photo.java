package com.sunbeaminfo.imageapp.model;

import android.graphics.Bitmap;

public class Photo
{
    int id;
    String author;
    String image;

    public Photo(){

    }
    public Photo(int id, String author,String image) {
        this.id = id;
        this.author = author;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
