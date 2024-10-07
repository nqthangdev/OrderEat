package com.ordereart.OrderEat.mapper;

import com.ordereart.OrderEat.dto.request.UserRequest;
import com.ordereart.OrderEat.dto.request.UserUpdateRequest;
import com.ordereart.OrderEat.dto.response.UserResponse;
import com.ordereart.OrderEat.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);

    void toUserUpdate(@MappingTarget User user, UserUpdateRequest request);

}
