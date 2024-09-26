package com.ordereart.OrderEat.controller;

import com.ordereart.OrderEat.dto.request.UserRequest;
import com.ordereart.OrderEat.dto.request.UserUpdateRequest;
import com.ordereart.OrderEat.dto.response.ApiResponse;
import com.ordereart.OrderEat.dto.response.UserResponse;
import com.ordereart.OrderEat.entity.Restaurant;
import com.ordereart.OrderEat.entity.User;
import com.ordereart.OrderEat.repository.RestaurantRepository;
import com.ordereart.OrderEat.repository.UserRepository;
import com.ordereart.OrderEat.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    UserRepository userRepository;
    RestaurantRepository restaurantRepository;

    //Create
    //User, Admin
    @PostMapping
    ApiResponse<UserResponse> create(@RequestBody @Valid UserRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    //Get all
    //Admin
    @GetMapping
    ApiResponse <List<UserResponse>> findAll(){
        log.info("Controller: get User");
        //Kiem tra token la cua User hay Admin
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .build();
    }

    //Get by Id
    //User, Admin
    @GetMapping("/{userId}")
    ApiResponse<UserResponse> findById(@PathVariable("userId") int id){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserById(id))
                .build();
    }

    //GetMyInfo
    @GetMapping("/myInfo")
    ApiResponse<UserResponse> findById(){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    //Update by Id
    //User, Admin
    @PutMapping("/{userId}")
    ApiResponse<UserResponse> update(@PathVariable("userId") int id, @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(id, request))
                .build();
    }

    //Delete by Id
    //Admin
    @DeleteMapping("/{userId}")
    ApiResponse<String> delete(@PathVariable("userId") int id){
        return ApiResponse.<String>builder()
                .result(userService.deleteUser(id))
                .build();
    }


    //Them menu vao user
    //User, Admin
    @PutMapping("/{userId}/restaurants/{restaurantId}")
    User updateUserRestaurant(@PathVariable int userId, @PathVariable int restaurantId){
        User user = userRepository.findById(userId).get();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        user.UserRestaurant(restaurant);
        return userRepository.save(user);
    }
}
