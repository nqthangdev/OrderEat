package com.ordereart.OrderEat.repository;

import com.ordereart.OrderEat.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    boolean existsByName(String name);

    List<Menu> findAllById(Iterable<Integer> menuId);
}
