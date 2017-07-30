package com.parse.starter.MainFragments;

/**
 * Created by Nils on 7/28/17.
 */

public class DataObject {
    private String text;
    private Integer image;

    DataObject (String text, Integer image){
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }


}
