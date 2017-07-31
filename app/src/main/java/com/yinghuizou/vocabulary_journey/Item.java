package com.yinghuizou.vocabulary_journey;

/**
 * Created by yinghuizou on 7/30/17.
 */

public class Item {
    private String title;
    private String definition;
    private String exmaple;
    private byte[] image;
    private int id;

    public Item(String title, String definition, String exmaple, byte[] image,int id) {
        this.title = title;
        this.definition = definition;
        this.exmaple = exmaple;
        this.image = image;
        this.id = id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setExmaple(String time) {
        this.exmaple = exmaple;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDefinition() {
        return definition;
    }

    public String getExmaple() {
        return exmaple;
    }

    public byte[] getImage() {
        return image;
    }


    public int getId() {
        return id;
    }
}