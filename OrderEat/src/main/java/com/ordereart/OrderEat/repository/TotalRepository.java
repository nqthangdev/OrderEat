package com.ordereart.OrderEat.repository;

import com.ordereart.OrderEat.entity.Total;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalRepository extends JpaRepository<Total, Integer> {
    boolean existsByName(String name);
}
