package com.ordereart.OrderEat.service;

import com.ordereart.OrderEat.dto.request.RestaurantRequest;
import com.ordereart.OrderEat.dto.response.RestaurantResponse;
import com.ordereart.OrderEat.entity.Restaurant;
import com.ordereart.OrderEat.exception.AppException;
import com.ordereart.OrderEat.exception.ErrorCode;
import com.ordereart.OrderEat.mapper.MenuMapper;
import com.ordereart.OrderEat.repository.RestaurantRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestaurantService {

    RestaurantRepository restaurantRepository;
    MenuMapper menuMapper;

    //Create
    public RestaurantResponse createRestaurant(RestaurantRequest request){

        Restaurant restaurant = menuMapper.toRestaurant(request);

        if (restaurantRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.EXISTS);
        }

        restaurant = restaurantRepository.save(restaurant);

        return menuMapper.toRestaurantResponse(restaurant);
    }

    //GetAll
    public List<RestaurantResponse> getAllRestaurant(){
        return restaurantRepository.findAll().stream().map(menuMapper::toRestaurantResponse).toList();
    }

    //GetId
    public RestaurantResponse getResById(int id){
        return menuMapper.toRestaurantResponse(restaurantRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND)));
    }

    //Update
    public RestaurantResponse updateRes(int id, RestaurantRequest request){
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND));

        menuMapper.toRestaurantUpdate(restaurant, request);

        Restaurant menus = restaurantRepository.save(restaurant);
        return menuMapper.toRestaurantResponse(menus);
    }

    //Delete
    public String deleteRes(int id){
        restaurantRepository.deleteById(id);
        return "Menu has been deleted !";
    }
}
