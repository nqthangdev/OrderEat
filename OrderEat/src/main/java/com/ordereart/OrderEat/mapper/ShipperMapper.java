package com.ordereart.OrderEat.mapper;

import com.ordereart.OrderEat.dto.request.ShipperRequest;
import com.ordereart.OrderEat.dto.response.ShipperResponse;
import com.ordereart.OrderEat.entity.Shipper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShipperMapper {
    Shipper toShipper(ShipperRequest request);

    ShipperResponse toShipperResponse(Shipper shipper);

}
