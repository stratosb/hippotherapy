package com.app.hippotherapy.model;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private boolean completed = false;
    private int rating = 0;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
