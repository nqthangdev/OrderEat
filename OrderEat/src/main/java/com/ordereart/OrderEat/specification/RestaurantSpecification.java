package com.ordereart.OrderEat.specification;

import com.ordereart.OrderEat.entity.Restaurant;
import org.springframework.data.jpa.domain.Specification;

public class RestaurantSpecification {

    public static Specification<Restaurant> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Restaurant> hasDescription(String description) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("description"), description);
    }

    public static Specification<Restaurant> hasPrice(String price) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("price"), price);
    }

}
