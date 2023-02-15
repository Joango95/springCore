package com.joango.model.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EventDTO {

    private Long id;

    private String title;

    private Date date;

    private Integer ticketPrice;

    public EventDTO(String title, Date date, Integer ticketPrice){
        this.title = title;
        this.date = date;
        this.ticketPrice = ticketPrice;
    }
}
