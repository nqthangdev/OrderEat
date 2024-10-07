package com.ordereart.OrderEat.controller;

import com.ordereart.OrderEat.dto.dto.UserDTO;
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
    ApiResponse<List<UserDTO>> getAll(){
        List<UserDTO> userDTO = userService.getAll();
        return ApiResponse.<List<UserDTO>>builder()
                .result(userDTO).build();
    }

    //Search by URL
    @GetMapping("/search")
    List<UserDTO> searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String location) {
        return userService.searchUser(username, name, location, phone);
    }

    //Get by input body
    @GetMapping("/all/search")
    public List<User> searchUsers(@RequestBody UserDTO userDTO) {
        return userService.searchUsers(userDTO.getUsername());
    }

    //Get by Id
    //User, Admin
    @GetMapping("/{userId}")
    ApiResponse<UserDTO> findById(@PathVariable("userId") int id){

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        log.info("Username: {}", authentication.getName());

        UserDTO userDTOS = userService.getUserById(id);

        return ApiResponse.<UserDTO>builder()
                .result(userDTOS)
                .build();
    }

    //GetMyInfo
    @GetMapping("/myInfo")
    ApiResponse<UserDTO> info(){
        return ApiResponse.<UserDTO>builder()
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
