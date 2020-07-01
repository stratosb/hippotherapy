package com.app.hippotherapy.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String name;
    private List<Session> sessions;
    private int badgesNo;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public int getBadgesNo() {
        return badgesNo;
    }

    public void setBadgesNo(int badgesNo) {
        this.badgesNo = badgesNo;
    }
}
