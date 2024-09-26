package com.ordereart.OrderEat.controller;

import com.ordereart.OrderEat.dto.request.RestaurantRequest;
import com.ordereart.OrderEat.dto.response.ApiResponse;
import com.ordereart.OrderEat.dto.response.RestaurantResponse;
import com.ordereart.OrderEat.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    final RestaurantService restaurantService;

    //Create Res
    @PostMapping
    ApiResponse<RestaurantResponse> create(@RequestBody @Valid RestaurantRequest request){
        return ApiResponse.<RestaurantResponse>builder()
                .result(restaurantService.createRestaurant(request))
                .build();
    }

    //Get All
    @GetMapping
    ApiResponse<List<RestaurantResponse>> findAll(){
        return ApiResponse.<List<RestaurantResponse>>builder()
                .result(restaurantService.getAllRestaurant())
                .build();
    }

    //Get Menu by Id
    @GetMapping("/{restaurantId}")
    ApiResponse<RestaurantResponse> findById(@PathVariable("restaurantId") int id){
        return ApiResponse.<RestaurantResponse>builder()
                .result(restaurantService.getResById(id))
                .build();
    }

    //Update Menu by Id
    @PutMapping("/{restaurantId}")
    ApiResponse<RestaurantResponse> update(@PathVariable("restaurantId") int id, @RequestBody RestaurantRequest request){
        return ApiResponse.<RestaurantResponse>builder()
                .result(restaurantService.updateRes(id, request))
                .build();
    }

    //Delete Menu by Id
    @DeleteMapping("/{restaurantId}")
    ApiResponse<String> delete(@PathVariable("restaurantId") int id){
        return ApiResponse.<String>builder()
                .result(restaurantService.deleteRes(id))
                .build();
    }
}
