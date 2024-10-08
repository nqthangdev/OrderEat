package com.ordereart.OrderEat.repository;

import com.ordereart.OrderEat.entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer>, JpaSpecificationExecutor<Shipper> {
}
