package com.ordereart.OrderEat.specification;

import com.ordereart.OrderEat.entity.Shipper;
import org.springframework.data.jpa.domain.Specification;

public class ShipperSpecification {

    public static Specification<Shipper> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Shipper> hasPhone(String phone) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("phone"), phone);
    }

    public static Specification<Shipper> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("email"), email);
    }
}
