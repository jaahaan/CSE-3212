package com.example.hello.model;

public class Model {
    private String title;
    private String subtitle;
    private int imageId;

    public Model() {

    }

    public Model(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public Model(String title, String subtitle, int imageId) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
