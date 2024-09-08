package com.ordereart.OrderEat.service;

import com.ordereart.OrderEat.dto.request.UserRequest;
import com.ordereart.OrderEat.dto.request.UserUpdateRequest;
import com.ordereart.OrderEat.entity.Menu;
import com.ordereart.OrderEat.entity.User;
import com.ordereart.OrderEat.exception.AppException;
import com.ordereart.OrderEat.exception.ErrorCode;
import com.ordereart.OrderEat.mapper.UserMapper;
import com.ordereart.OrderEat.repository.MenuRepository;
import com.ordereart.OrderEat.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService
{
    UserRepository userRepository;
    UserMapper userMapper;
    private final MenuRepository menuRepository;

    public User createUser(UserRequest request){
        User user = userMapper.toUser(request);

        if (userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.EXISTS);
        }

        user = userRepository.save(user);

        return userMapper.toUserDisplay(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toUserDisplay).toList();
    }

    public User getUserById(int id){
        return userMapper.toUserDisplay(userRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND)));
    }

    //Update
    public User updateUser(int id, UserUpdateRequest request){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND));

        if (userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.EXISTS);
        }

        userMapper.toUserUpdate(user, request);

        return userMapper.toUserDisplay(userRepository.save(user));
    }

    //Delete
    public User deleteUser(int id){
        userRepository.deleteById(id);
        return null;
    }

    public User updateUserMenus(int userId, List<Integer> menuIds) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Menu> menus = new HashSet<>(menuRepository.findAllById(menuIds));

        user.setMenus(menus);
        return userRepository.save(user);
    }
}
