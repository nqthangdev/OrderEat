package com.ordereart.OrderEat.mapper;

import com.ordereart.OrderEat.dto.request.ShipperRequest;
import com.ordereart.OrderEat.dto.response.ShipperResponse;
import com.ordereart.OrderEat.entity.Shipper;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-26T15:55:00+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ShipperMapperImpl implements ShipperMapper {

    @Override
    public Shipper toShipper(ShipperRequest request) {
        if ( request == null ) {
            return null;
        }

        Shipper shipper = new Shipper();

        shipper.setName( request.getName() );

        return shipper;
    }

    @Override
    public ShipperResponse toShipperResponse(Shipper shipper) {
        if ( shipper == null ) {
            return null;
        }

        ShipperResponse.ShipperResponseBuilder shipperResponse = ShipperResponse.builder();

        shipperResponse.id( shipper.getId() );
        shipperResponse.name( shipper.getName() );
        shipperResponse.user( shipper.getUser() );

        return shipperResponse.build();
    }
}
