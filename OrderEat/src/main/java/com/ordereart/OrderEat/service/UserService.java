package com.ordereart.OrderEat.service;

import com.ordereart.OrderEat.dto.dto.UserDTO;
import com.ordereart.OrderEat.dto.request.UserRequest;
import com.ordereart.OrderEat.dto.request.UserUpdateRequest;
import com.ordereart.OrderEat.dto.response.UserResponse;
import com.ordereart.OrderEat.entity.User;
import com.ordereart.OrderEat.enums.Role;
import com.ordereart.OrderEat.exception.AppException;
import com.ordereart.OrderEat.exception.ErrorCode;
import com.ordereart.OrderEat.mapper.UserMapper;
import com.ordereart.OrderEat.repository.UserRepository;
import com.ordereart.OrderEat.specification.UserSpecification;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService
{
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setPhone(user.getPhone());
        userDTO.setLocation(user.getLocation());
        userDTO.setRoles(user.getRoles());
        userDTO.setRestaurants(user.getRestaurants());
        return userDTO;
    }

    //Create
    public UserResponse createUser(UserRequest request){
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.EXISTS);
        }

        //Role
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    //Get All
    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
    }

    //Search in URL
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> searchUser(String username, String name, String phone, String location) {

        Specification<User> spec = Specification.where(null);

        if (username != null && !username.isEmpty()) {
            spec = spec.and(UserSpecification.hasUsername(username));
        }

        if (name != null && !name.isEmpty()) {
            spec = spec.and(UserSpecification.hasName(name));
        }

        if (phone != null && !phone.isEmpty()) {
            spec = spec.and(UserSpecification.hasPhone(phone));
        }

        if (location != null && !location.isEmpty()) {
            spec = spec.and(UserSpecification.hasLocation(location));
        }

        return userRepository.findAll(spec)
                            .stream()
                            .map(this::convertToDTO)
                            .collect(Collectors.toList());
    }

    //Search in Body
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> searchUsers(String username) {
        return userRepository.findAll(UserSpecification.hasUsername(username));
    }

    //GetById
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO getUserById(int id){

        User user = userRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND));

        return convertToDTO(user);
    }

    //GetMyInfo
    public UserDTO getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.NOTFOUND));

        return convertToDTO(user);
    }

    //Update
    public UserResponse updateUser(int id, UserUpdateRequest request){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND));

        userMapper.toUserUpdate(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    //Delete
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(int id){
        userRepository.deleteById(id);
        return "User has been deleted !";
    }
}
