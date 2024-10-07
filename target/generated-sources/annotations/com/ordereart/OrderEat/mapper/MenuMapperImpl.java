package com.ordereart.OrderEat.mapper;

import com.ordereart.OrderEat.dto.dto.RestaurantDTO;
import com.ordereart.OrderEat.dto.request.RestaurantRequest;
import com.ordereart.OrderEat.dto.response.RestaurantResponse;
import com.ordereart.OrderEat.entity.Restaurant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-03T16:11:04+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class MenuMapperImpl implements MenuMapper {

    @Override
    public Restaurant toRestaurant(RestaurantRequest request) {
        if ( request == null ) {
            return null;
        }

        Restaurant.RestaurantBuilder restaurant = Restaurant.builder();

        restaurant.name( request.getName() );
        restaurant.description( request.getDescription() );
        restaurant.price( request.getPrice() );

        return restaurant.build();
    }

    @Override
    public RestaurantResponse toRestaurantResponse(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantResponse.RestaurantResponseBuilder restaurantResponse = RestaurantResponse.builder();

        restaurantResponse.id( restaurant.getId() );
        restaurantResponse.name( restaurant.getName() );
        restaurantResponse.description( restaurant.getDescription() );
        restaurantResponse.price( restaurant.getPrice() );

        return restaurantResponse.build();
    }

    @Override
    public void toRestaurantUpdate(Restaurant restaurant, RestaurantRequest request) {
        if ( request == null ) {
            return;
        }

        restaurant.setName( request.getName() );
        restaurant.setDescription( request.getDescription() );
        restaurant.setPrice( request.getPrice() );
    }

    @Override
    public RestaurantDTO toRestaurantDTO(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantDTO.RestaurantDTOBuilder restaurantDTO = RestaurantDTO.builder();

        restaurantDTO.id( restaurant.getId() );
        restaurantDTO.name( restaurant.getName() );
        restaurantDTO.description( restaurant.getDescription() );
        restaurantDTO.price( restaurant.getPrice() );

        return restaurantDTO.build();
    }
}
