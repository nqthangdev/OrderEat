package com.ordereart.OrderEat.controller;

import com.ordereart.OrderEat.dto.request.UserRequest;
import com.ordereart.OrderEat.dto.request.UserUpdateRequest;
import com.ordereart.OrderEat.dto.response.ApiResponse;
import com.ordereart.OrderEat.entity.Menu;
import com.ordereart.OrderEat.entity.User;
import com.ordereart.OrderEat.repository.MenuRepository;
import com.ordereart.OrderEat.repository.UserRepository;
import com.ordereart.OrderEat.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    UserRepository userRepository;
    MenuRepository menuRepository;

    //Create User
    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserRequest request){
        return ApiResponse.<User>builder()
                .result(userService.createUser(request))
                .build();
    }

    //Get all User
    @GetMapping
    ApiResponse <List<User>> findAll(){
        return ApiResponse.<List<User>>builder()
                .result(userService.getAllUsers())
                .build();
    }

    //Get User by Id
    @GetMapping("/{userId}")
    ApiResponse<User> findById(@PathVariable("userId") int id){
        return ApiResponse.<User>builder()
                .result(userService.getUserById(id))
                .build();
    }

    //Update User by Id
    @PutMapping("/{userId}")
    ApiResponse<User> updateUser(@PathVariable("userId") int id, @RequestBody UserUpdateRequest request){
        return ApiResponse.<User>builder()
                .result(userService.updateUser(id, request))
                .build();
    }

    //Delete User by Id
    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable("userId") int id){
        return ApiResponse.<String>builder()
                .result("Has been deleted !")
                .build();
    }

    @PutMapping("/{userId}/menu/{menuId}")
    User updateUserMenu(@PathVariable int userId, @PathVariable int menuId){
        User user = userRepository.findById(userId).get();
        Menu menu = menuRepository.findById(menuId).get();
        user.UserMenu(menu);
        return userRepository.save(user);
    }
}
