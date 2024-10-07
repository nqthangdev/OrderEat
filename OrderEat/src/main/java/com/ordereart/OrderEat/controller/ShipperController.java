package com.ordereart.OrderEat.controller;

import com.ordereart.OrderEat.dto.dto.ShipperDTO;
import com.ordereart.OrderEat.dto.dto.UserDTO;
import com.ordereart.OrderEat.dto.request.ShipperRequest;
import com.ordereart.OrderEat.dto.response.ApiResponse;
import com.ordereart.OrderEat.dto.response.ShipperResponse;
import com.ordereart.OrderEat.repository.ShipperRepository;
import com.ordereart.OrderEat.service.ShipperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shippers")
@RequiredArgsConstructor
public class ShipperController {
    ShipperRepository shipperRepository;
    @Autowired
    ShipperService shipperService;

    //Create
    @PostMapping
    ApiResponse<ShipperResponse> create(@RequestBody @Valid ShipperRequest request){
        return ApiResponse.<ShipperResponse>builder()
                .result(shipperService.create(request))
                .build();
    }

    //GetAll
    @GetMapping
    ApiResponse<List<ShipperDTO>> getAll(){
        List<ShipperDTO> shipperDTO = shipperService.getAll();
        return ApiResponse.<List<ShipperDTO>>builder()
                .result(shipperDTO).build();
    }

    //GetById
    @GetMapping("/{shipperId}")
    ApiResponse<ShipperDTO> getById(@PathVariable("shipperId") int id){
        return ApiResponse.<ShipperDTO>builder()
                .result(shipperService.getById(id)).build();
    }

    //Delete
    @DeleteMapping("/{shipperId}")
    ApiResponse<String> delete(@PathVariable("shipperId") int id){
        return ApiResponse.<String>builder()
                .result(shipperService.delete(id))
                .build();
    }

}
