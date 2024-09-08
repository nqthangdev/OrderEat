//package com.ordereart.OrderEat.controller;
//
//import com.ordereart.OrderEat.dto.request.ShipperRequest;
//import com.ordereart.OrderEat.dto.response.ApiResponse;
//import com.ordereart.OrderEat.entity.Shipper;
//import com.ordereart.OrderEat.repository.ShipperRepository;
//import com.ordereart.OrderEat.service.ShipperService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/shippers")
//@RequiredArgsConstructor
//public class ShipperController {
//    final ShipperService shipperService;
//    private final ShipperRepository shipperRepository;
//
//    //Create
//    @PostMapping
//    ApiResponse<Shipper> createShipper(@RequestBody @Valid ShipperRequest request){
//        return ApiResponse.<Shipper>builder()
//                .result(shipperService.createMenu(request))
//                .build();
//    }
//
//    //Get All
//    @GetMapping
//    ApiResponse<List<Shipper>> findAll(){
//        return ApiResponse.<List<Shipper>>builder()
//                .result(shipperService.getAllShipper())
//                .build();
//    }
//
//    //Get by Id
//    @GetMapping("/{shipperId}")
//    ApiResponse<Shipper> findById(@PathVariable("shipperId") int id){
//        return ApiResponse.<Shipper>builder()
//                .result(shipperService.getShipperById(id))
//                .build();
//    }
//
//    //Update by Id
//    @PutMapping("/{shipperId}")
//    ApiResponse<Shipper> update(@PathVariable("shipperId") int id, @RequestBody ShipperRequest request){
//        return ApiResponse.<Shipper>builder()
//                .result(shipperService.updateShipper(id, request))
//                .build();
//    }
//
//    //Delete by Id
//    @DeleteMapping("/{shipperId}")
//    ApiResponse<String> deleteMenu(@PathVariable("shipperId") int id){
//        return ApiResponse.<String>builder()
//                .result("Has been deleted !")
//                .build();
//    }
//}
