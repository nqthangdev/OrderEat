package com.ordereart.OrderEat.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipperRequest {
    String name;
    String phone;
    String email;
    Set<String> userId;
}
