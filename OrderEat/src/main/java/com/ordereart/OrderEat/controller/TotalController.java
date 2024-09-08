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

    @PostMapping
    ApiResponse<TotalResponse> create(@RequestBody @Valid TotalRequest request){
        return ApiResponse.<TotalResponse>builder()
                .result(totalService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<Total>> getAll(){
        return ApiResponse.<List<Total>>builder()
                .result(totalService.getAlTotal()).build();
    }
}
