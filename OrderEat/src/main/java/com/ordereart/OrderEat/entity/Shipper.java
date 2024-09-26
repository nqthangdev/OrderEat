package com.ordereart.OrderEat.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "shipper")
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String phone;
    String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
