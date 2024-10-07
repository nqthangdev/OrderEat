package com.ordereart.OrderEat.specification;

import com.ordereart.OrderEat.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification {

    public static Specification<User> hasUsername(String username) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("username"), username);
    }

    public static Specification<User> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<User> hasPhone(String phone) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("phone"), phone);
    }

    public static Specification<User> hasLocation(String location) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("location"), location);
    }
}
