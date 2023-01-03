package com.joango.model.DTO;

import com.joango.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDTO {

    private Long id;

    private Long eventId;

    private Long userId;

    private Category category;

    private Integer place;

    public TicketDTO(
        Long eventId,
        Long userId,
        Category category,
        Integer place
    ){
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }

}
