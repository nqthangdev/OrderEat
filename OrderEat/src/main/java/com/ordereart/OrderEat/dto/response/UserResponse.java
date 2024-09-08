package com.ordereart.OrderEat.dto.response;

import com.ordereart.OrderEat.entity.Menu;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    int id;
    String username;
    String password;
    String name;
    String phone;
    String location;
    Set<Menu> menus;
}
