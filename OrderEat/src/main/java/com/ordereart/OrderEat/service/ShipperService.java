//package com.ordereart.OrderEat.service;
//
//import com.ordereart.OrderEat.dto.request.MenuRequest;
//import com.ordereart.OrderEat.dto.request.ShipperRequest;
//import com.ordereart.OrderEat.entity.Menu;
//import com.ordereart.OrderEat.entity.Shipper;
//import com.ordereart.OrderEat.exception.AppException;
//import com.ordereart.OrderEat.exception.ErrorCode;
//import com.ordereart.OrderEat.mapper.ShipperMapper;
//import com.ordereart.OrderEat.repository.ShipperRepository;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class ShipperService {
//    ShipperRepository shipperRepository;
//    ShipperMapper shipperMapper;
//
//    //Create
//    public Shipper createMenu(ShipperRequest request){
//        Shipper shipper = shipperMapper.toShipper(request);
//
//        if (shipperRepository.existsByName(request.getName())){
//            throw new AppException(ErrorCode.EXISTS);
//        }
//
//        shipper = shipperRepository.save(shipper);
//
//        return shipperMapper.toShipperDisplay(shipper);
//    }
//
//    //GetAll
//    public List<Shipper> getAllShipper(){
//        return shipperRepository.findAll().stream().map(shipperMapper::toShipperDisplay).toList();
//    }
//
//    //GetId
//    public Shipper getShipperById(int id){
//        return shipperMapper.toShipperDisplay(shipperRepository.findById(id)
//                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND)));
//    }
//
//    //Update
//    public Shipper updateShipper(int id, ShipperRequest request){
//        Shipper shipper = shipperRepository.findById(id)
//                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND));
//
//        shipperMapper.toShipperUpdate(shipper, request);
//
//        Shipper shippers = shipperRepository.save(shipper);
//        return shipperMapper.toShipperDisplay(shippers);
//    }
//
//    //Delete
//    public void deleteShipper(int id){
//        shipperRepository.deleteById(id);
//    }
//}
