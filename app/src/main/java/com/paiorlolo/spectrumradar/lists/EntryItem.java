package com.paiorlolo.spectrumradar.lists;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class EntryItem implements Item {

    private String title;
    private String subTitle;
    private Boolean selected;
    private Boolean isLength;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean isLength() {
        return isLength;
    }

    public void setLength(Boolean length) {
        isLength = length;
    }

    public EntryItem(String title, String subTitle, Boolean isLength) {
        this.title = title;
        this.subTitle = subTitle;
        this.isLength = isLength;
        selected = false;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    @Override
    public boolean isSection() {
        return false;
    }
}
