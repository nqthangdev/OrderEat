package com.ordereart.OrderEat.dto.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    int id;
    String name;
    String description;
    int price;
}
