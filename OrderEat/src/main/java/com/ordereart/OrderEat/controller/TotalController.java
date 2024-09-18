package com.ordereart.OrderEat.controller;

import com.ordereart.OrderEat.dto.request.TotalRequest;
import com.ordereart.OrderEat.dto.response.ApiResponse;
import com.ordereart.OrderEat.dto.response.TotalResponse;
import com.ordereart.OrderEat.entity.Total;
import com.ordereart.OrderEat.repository.TotalRepository;
import com.ordereart.OrderEat.service.TotalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/totals")
@RequiredArgsConstructor
public class TotalController {
    TotalRepository totalRepository;
    @Autowired
    TotalService totalService;

    //Create
    @PostMapping
    ApiResponse<TotalResponse> create(@RequestBody @Valid TotalRequest request){
        return ApiResponse.<TotalResponse>builder()
                .result(totalService.create(request))
                .build();
    }

    //GetAll
    @GetMapping
    ApiResponse<List<TotalResponse>> getAll(){
        return ApiResponse.<List<TotalResponse>>builder()
                .result(totalService.getAlTotal()).build();
    }

    //GetById
    @GetMapping("/{totalId}")
    ApiResponse<TotalResponse> getById(@PathVariable("totalId") int id){
        return ApiResponse.<TotalResponse>builder()
                .result(totalService.getTotalById(id)).build();
    }

    //Delete
    @DeleteMapping("/{totalId}")
    ApiResponse<TotalResponse> delete(@PathVariable("totalId") int id){
        return ApiResponse.<TotalResponse>builder()
                .result(totalService.deleteTotal(id))
                .build();
    }

}
