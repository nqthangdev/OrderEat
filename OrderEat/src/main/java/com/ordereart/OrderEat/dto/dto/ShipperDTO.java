package com.ordereart.OrderEat.dto.dto;

import com.ordereart.OrderEat.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipperDTO {

    int id;
    String name;
    String phone;
    String email;
    User user;
}
