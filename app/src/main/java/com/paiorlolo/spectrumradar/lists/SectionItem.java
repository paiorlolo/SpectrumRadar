package com.paiorlolo.spectrumradar.lists;

public class SectionItem implements Item {

    private String title;

    public SectionItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean isSection() {
        return true;
    }
}
