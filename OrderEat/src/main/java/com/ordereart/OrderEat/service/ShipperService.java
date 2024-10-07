package com.ordereart.OrderEat.service;

import com.ordereart.OrderEat.dto.dto.ShipperDTO;
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

    public ShipperDTO shipperDTO(Shipper shipper){
        return new ShipperDTO(
            shipper.getId(),
            shipper.getName(),
            shipper.getPhone(),
            shipper.getEmail(),
            shipper.getUser()
        );
    }

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
    public List<ShipperDTO> getAll(){
        List<Shipper> shippers = shipperRepository.findAll();

        return shippers.stream()
                    .map(this::shipperDTO)
                    .collect(Collectors.toList());
    }

    //GetById
    public ShipperDTO getById(int id){
        Shipper shipper = shipperRepository.findById(id)
                        .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND));

        return shipperDTO(shipper);
    }

    //Delete
    public String delete(int id){
        shipperRepository.deleteById(id);
        return "Has been deleted !";
    }
}
