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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String username;
    String password;
    String name;
    String phone;
    String location;
    Set<String> roles;

    @ManyToMany
    @JoinTable(
            name = "total",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    Set<Restaurant> restaurants = new HashSet<>();

    public void UserRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
    }


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    Set<Shipper> shippers = new HashSet<>();
}
