package com.example.hello.D;

public class ModelD {
    private String title;
    private String subtitle;
    private int imageId;

    public ModelD(String title, String subtitle, int imageId) {
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
