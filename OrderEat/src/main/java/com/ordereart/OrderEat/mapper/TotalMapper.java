package com.ordereart.OrderEat.mapper;

import com.ordereart.OrderEat.dto.request.TotalRequest;
import com.ordereart.OrderEat.entity.Total;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TotalMapper {
    Total toTotal(TotalRequest request);

    Total toTotalDisplay(Total total);

}
