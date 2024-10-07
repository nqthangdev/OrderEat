package com.ordereart.OrderEat.service;

import com.ordereart.OrderEat.dto.dto.RestaurantDTO;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestaurantService {

    RestaurantRepository restaurantRepository;
    MenuMapper menuMapper;

    public RestaurantDTO restaurantDTO(Restaurant restaurant){
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        return restaurantDTO;
    }


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
    public List<RestaurantDTO> getAllRestaurant(){
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants.stream()
                .map(this::restaurantDTO)
                .collect(Collectors.toList());
    }

    //GetId
    public RestaurantDTO getResById(int id){
        Restaurant restaurant =restaurantRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND));

        return restaurantDTO(restaurant);
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
