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
public class TotalRequest {
    String name;
    Set<String> userId;
//    Set<String> menuId;
}
