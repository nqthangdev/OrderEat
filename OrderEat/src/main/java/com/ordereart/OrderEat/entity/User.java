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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String username;
    String password;
    String name;
    String phone;
    String location;

    @ManyToMany
    @JoinTable(
            name = "user_menu",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    Set<Menu> menus = new HashSet<>();

    public void UserMenu(Menu menu){
        menus.add(menu);
    }


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    Set<Total> totals = new HashSet<>();
}
