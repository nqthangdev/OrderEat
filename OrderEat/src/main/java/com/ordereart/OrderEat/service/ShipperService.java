package com.ordereart.OrderEat.service;

import com.ordereart.OrderEat.dto.request.ShipperRequest;
import com.ordereart.OrderEat.dto.response.ShipperResponse;
import com.ordereart.OrderEat.entity.Shipper;
import com.ordereart.OrderEat.entity.User;
import com.ordereart.OrderEat.exception.AppException;
import com.ordereart.OrderEat.exception.ErrorCode;
import com.ordereart.OrderEat.mapper.ShipperMapper;
import com.ordereart.OrderEat.repository.ShipperRepository;
import com.ordereart.OrderEat.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShipperService {

    ShipperMapper shipperMapper;
    ShipperRepository shipperRepository;
    UserRepository userRepository;

    //Create
    public ShipperResponse create(ShipperRequest request){
        Shipper shipper = shipperMapper.toShipper(request);

        Set<Integer> userId = request.getUserId().stream()
                                     .map(Integer::valueOf)
                                     .collect(Collectors.toSet());

        List<User> users = userRepository.findAllById(userId);

        for (User user : users) {
            Shipper shippers = new Shipper();
            shippers.setName(request.getName());
            shippers.setPhone(request.getPhone());
            shippers.setEmail(request.getEmail());
            shippers.setUser(user);
            shipper = shipperRepository.save(shippers);
        }

        shipper = shipperRepository.save(shipper);
        return shipperMapper.toShipperResponse(shipper);
    }

    //GetAll
    @PreAuthorize("hasRole('ADMIN')")
    public List<ShipperResponse> getAll(){
        return shipperRepository.findAll()
                            .stream()
                            .map(shipperMapper::toShipperResponse)
                            .toList();
    }

    //GetById
    public ShipperResponse getById(int id){
        return shipperMapper.toShipperResponse(shipperRepository.findById(id)
                        .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND)));
    }

    //Delete
    public String delete(int id){
        shipperRepository.deleteById(id);
        return "Total has been deleted !";
    }
}
