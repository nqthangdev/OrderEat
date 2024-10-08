package com.ordereart.OrderEat.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ordereart.OrderEat.dto.dto.UserDTO;
import com.ordereart.OrderEat.entity.Restaurant;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse{

    int id;
    String username;
    String password;
    String name;
    String phone;
    String location;
    Set<String> roles;
    Set<Restaurant> restaurants;
}
