package com.xias.piechat.entry;

import java.io.Serializable;

/**
 * Created by XIAS on 2017/8/16.
 */

public class PieChatEntry implements Serializable {
    private int color;
    private boolean isSelected;
    private float startAngle;
    private float endAngle;
    private String name;

    public PieChatEntry(String name, int color, boolean isSelected, float startAngle, float endAngle) {
        this.name = name;
        this.color = color;
        this.isSelected = isSelected;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public float getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
    }

    public float getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(float endAngle) {
        this.endAngle = endAngle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
