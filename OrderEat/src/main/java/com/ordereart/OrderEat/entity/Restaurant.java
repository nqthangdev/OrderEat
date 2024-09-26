package com.ordereart.OrderEat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String name;
    String description;
    int price;

    @JsonIgnore
    @ManyToMany(mappedBy = "restaurants")
    Set<User> users = new HashSet<>();
}
