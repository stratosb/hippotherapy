package com.app.hippotherapy.model;

import java.io.Serializable;
import java.util.List;

/**
 * Session -> day
 * 10 Sessions
 * 10 Badges
 * 1 Session = 10 tasks
 */
public class Session extends Entity implements Serializable {
    private List<Task> tasks;
    private String comment;
    private Long dateCompleted;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Long dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
}
