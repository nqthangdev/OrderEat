package com.ordereart.OrderEat.mapper;

import com.ordereart.OrderEat.dto.request.MenuRequest;
import com.ordereart.OrderEat.dto.response.MenuResponse;
import com.ordereart.OrderEat.entity.Menu;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-18T16:49:56+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class MenuMapperImpl implements MenuMapper {

    @Override
    public Menu toMenu(MenuRequest request) {
        if ( request == null ) {
            return null;
        }

        Menu.MenuBuilder menu = Menu.builder();

        menu.name( request.getName() );
        menu.description( request.getDescription() );
        menu.price( request.getPrice() );

        return menu.build();
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
    public void toMenuUpdate(Menu menu, MenuRequest request) {
        if ( request == null ) {
            return;
        }

        menu.setName( request.getName() );
        menu.setDescription( request.getDescription() );
        menu.setPrice( request.getPrice() );
    }
}
