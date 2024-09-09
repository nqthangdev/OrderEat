package com.ordereart.OrderEat.mapper;

import com.ordereart.OrderEat.dto.request.UserRequest;
import com.ordereart.OrderEat.dto.request.UserUpdateRequest;
import com.ordereart.OrderEat.dto.response.UserResponse;
import com.ordereart.OrderEat.entity.Menu;
import com.ordereart.OrderEat.entity.Total;
import com.ordereart.OrderEat.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-09T09:52:32+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( request.getUsername() );
        user.setPassword( request.getPassword() );
        user.setName( request.getName() );
        user.setPhone( request.getPhone() );
        user.setLocation( request.getLocation() );

        return user;
    }

    @Override
    public User toUserDisplay(User user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setId( user.getId() );
        user1.setUsername( user.getUsername() );
        user1.setPassword( user.getPassword() );
        user1.setName( user.getName() );
        user1.setPhone( user.getPhone() );
        user1.setLocation( user.getLocation() );
        Set<Menu> set = user.getMenus();
        if ( set != null ) {
            user1.setMenus( new LinkedHashSet<Menu>( set ) );
        }
        Set<Total> set1 = user.getTotals();
        if ( set1 != null ) {
            user1.setTotals( new LinkedHashSet<Total>( set1 ) );
        }

        return user1;
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.password( user.getPassword() );
        userResponse.name( user.getName() );
        userResponse.phone( user.getPhone() );
        userResponse.location( user.getLocation() );
        Set<Menu> set = user.getMenus();
        if ( set != null ) {
            userResponse.menus( new LinkedHashSet<Menu>( set ) );
        }

        return userResponse.build();
    }

    @Override
    public void toUserUpdate(User user, UserUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        user.setUsername( request.getUsername() );
        user.setPassword( request.getPassword() );
        user.setName( request.getName() );
        user.setPhone( request.getPhone() );
        user.setLocation( request.getLocation() );
    }
}
