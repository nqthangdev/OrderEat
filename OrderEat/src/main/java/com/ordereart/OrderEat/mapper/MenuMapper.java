package com.ordereart.OrderEat.mapper;

import com.ordereart.OrderEat.dto.dto.RestaurantDTO;
import com.ordereart.OrderEat.dto.request.RestaurantRequest;
import com.ordereart.OrderEat.dto.response.RestaurantResponse;
import com.ordereart.OrderEat.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

//RESTAURANT
@Mapper(componentModel = "spring")
public interface MenuMapper {
    Restaurant toRestaurant(RestaurantRequest request);

    RestaurantResponse toRestaurantResponse(Restaurant restaurant);

    void toRestaurantUpdate(@MappingTarget Restaurant restaurant, RestaurantRequest request);

    RestaurantDTO toRestaurantDTO(Restaurant restaurant);
}
