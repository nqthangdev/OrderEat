package com.ordereart.OrderEat.repository;

import com.ordereart.OrderEat.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>, JpaSpecificationExecutor<Restaurant> {
    boolean existsByName(String name);

    List<Restaurant> findAllById(Iterable<Integer> menuId);
}
