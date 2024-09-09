package com.ordereart.OrderEat.mapper;

import com.ordereart.OrderEat.dto.request.MenuRequest;
import com.ordereart.OrderEat.dto.response.MenuResponse;
import com.ordereart.OrderEat.entity.Menu;
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
public class MenuMapperImpl implements MenuMapper {

    @Override
    public Menu toMenu(MenuRequest request) {
        if ( request == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setName( request.getName() );
        menu.setDescription( request.getDescription() );
        menu.setPrice( request.getPrice() );

        return menu;
    }

    @Override
    public MenuResponse toMenuResponse(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        MenuResponse.MenuResponseBuilder menuResponse = MenuResponse.builder();

        menuResponse.id( menu.getId() );
        menuResponse.name( menu.getName() );
        menuResponse.description( menu.getDescription() );
        menuResponse.price( menu.getPrice() );

        return menuResponse.build();
    }

    @Override
    public Menu toMenuDisplay(Menu menu) {
        if ( menu == null ) {
            return null;
        }

        Menu menu1 = new Menu();

        menu1.setId( menu.getId() );
        menu1.setName( menu.getName() );
        menu1.setDescription( menu.getDescription() );
        menu1.setPrice( menu.getPrice() );
        Set<User> set = menu.getUsers();
        if ( set != null ) {
            menu1.setUsers( new LinkedHashSet<User>( set ) );
        }

        return menu1;
    }

    @Override
    public void toMenuUpdate(Menu menu, MenuRequest request) {
        if ( request == null ) {
            return;
        }

        menu.setName( request.getName() );
        menu.setDescription( request.getDescription() );
        menu.setPrice( request.getPrice() );
    }
}
