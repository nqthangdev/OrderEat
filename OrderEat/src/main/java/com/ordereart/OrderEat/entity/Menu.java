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
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String name;
    String description;
    int price;

    @JsonIgnore
    @ManyToMany(mappedBy = "menus")
    Set<User> users = new HashSet<>();

//    @JsonIgnore
//    @OneToMany(mappedBy = "menu")
//    Set<Total> totals = new HashSet<>();
}
