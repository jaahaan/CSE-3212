package com.example.hello.recyclerView;

public class Model {

    private String title;
    private String subtitle;
    private int imageID;

    public Model(String title, String subtitle, int imageID) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
