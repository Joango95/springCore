package com.joango.model.DTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAccountDTO {

    private Long userId;
    private Integer userBalance;

}
