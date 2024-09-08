package com.ordereart.OrderEat.dto.response;

import com.ordereart.OrderEat.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TotalResponse {
    int id;

    String name;
    User user;
}
