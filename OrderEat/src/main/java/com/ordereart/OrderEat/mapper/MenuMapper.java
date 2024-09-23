package com.ordereart.OrderEat.mapper;

import com.ordereart.OrderEat.dto.request.MenuRequest;
import com.ordereart.OrderEat.dto.response.MenuResponse;
import com.ordereart.OrderEat.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    Menu toMenu(MenuRequest request);

    MenuResponse toMenuResponse(Menu menu);

    void toMenuUpdate(@MappingTarget Menu menu, MenuRequest request);
}
