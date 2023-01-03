package com.joango.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TICKET")
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EVENT_ID")
    private Long eventId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "PLACE")
    private Integer place;

    public Ticket(long userId, long eventId, int place, Category category) {
        this.userId = userId;
        this.eventId = eventId;
        this.place = place;
        this.category = category;
    }
}
