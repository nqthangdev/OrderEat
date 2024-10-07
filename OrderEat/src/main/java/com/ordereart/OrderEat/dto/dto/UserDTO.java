package com.ordereart.OrderEat.dto.dto;

import com.ordereart.OrderEat.entity.Restaurant;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    int id;
    String username;
    String password;
    String name;
    String phone;
    String location;
    Set<String> roles;
    Set<Restaurant> restaurants;
}
