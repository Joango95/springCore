package com.joango.DAO;

import com.joango.model.Event;

import java.util.Date;

public class EventDAO implements Event {

    private long id;
    private String title;
    private Date date;

    public EventDAO(long id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public EventDAO(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void setId(long id) {

    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public Date getDate() {
        return null;
    }

    @Override
    public void setDate(Date date) {

    }
}
