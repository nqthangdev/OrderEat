package com.ordereart.OrderEat.mapper;

import com.ordereart.OrderEat.dto.request.TotalRequest;
import com.ordereart.OrderEat.dto.response.TotalResponse;
import com.ordereart.OrderEat.entity.Total;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-08T10:24:50+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class TotalMapperImpl implements TotalMapper {

    @Override
    public Total toTotal(TotalRequest request) {
        if ( request == null ) {
            return null;
        }

        Total total = new Total();

        total.setName( request.getName() );

        return total;
    }

    @Override
    public TotalResponse toTotalResponse(Total total) {
        if ( total == null ) {
            return null;
        }

        TotalResponse.TotalResponseBuilder totalResponse = TotalResponse.builder();

        totalResponse.id( total.getId() );
        totalResponse.name( total.getName() );
        totalResponse.user( total.getUser() );

        return totalResponse.build();
    }

    @Override
    public Total toTotalDisplay(Total total) {
        if ( total == null ) {
            return null;
        }

        Total total1 = new Total();

        total1.setId( total.getId() );
        total1.setName( total.getName() );
        total1.setUser( total.getUser() );

        return total1;
    }
}
