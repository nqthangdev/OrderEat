package com.ordereart.OrderEat.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @Size(min = 8, message = "USER_LARGER")
    String username;
    String password;
    String name;
    String phone;
    String location;
}
