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

    public EventDTO(String title, Date date){
        this.title = title;
        this.date = date;
    }
}
