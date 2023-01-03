package com.joango.model.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private String email;

    public UserDTO(Long id){
        this.id = id;
    }

    public UserDTO(String name, String email){
        this.name = name;
        this.email = email;
    }
}
