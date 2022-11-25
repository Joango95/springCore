package com.joango.DAO;

import com.joango.model.Ticket;

public class TicketDAO implements Ticket {

    private Long id;
    private long eventId;
    private long userId;
    private Category category;
    private int place;

    public TicketDAO(
        long id,
        long userId,
        long eventId,
        int place,
        Ticket.Category category
    ) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.place = place;
        this.category = category;
    }

    public TicketDAO(
        long userId,
        long eventId,
        int place,
        Ticket.Category category
    ) {
        this.userId = userId;
        this.eventId = eventId;
        this.place = place;
        this.category = category;
    }


    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getEventId() {
        return this.eventId;
    }

    @Override
    public void setEventId(long eventId) {
        this.eventId =eventId;
    }

    @Override
    public long getUserId() {
        return this.userId;
    }

    @Override
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int getPlace() {
        return this.place;
    }

    @Override
    public void setPlace(int place) {
        this.place = place;
    }
}
