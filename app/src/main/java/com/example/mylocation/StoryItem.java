package com.example.mylocation;

public class StoryItem {

    private String Story;
    private String name;
    private int im;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StoryItem(String story, int im, String name) {
        Story = story;
        this.im = im;
        this.name = name;
    }

    public String getStory() {
        return Story;
    }

    public void setStory(String story) {
        Story = story;
    }

    public int getIm() {
        return im;
    }

    public void setIm(int im) {
        this.im = im;
    }


}
