package com.ordereart.OrderEat.controller;

import com.ordereart.OrderEat.dto.request.MenuRequest;
import com.ordereart.OrderEat.dto.response.ApiResponse;
import com.ordereart.OrderEat.dto.response.MenuResponse;
import com.ordereart.OrderEat.repository.MenuRepository;
import com.ordereart.OrderEat.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    final MenuService menuService;
    private final MenuRepository menuRepository;

    //Create Menu
    @PostMapping
    ApiResponse<MenuResponse> createMenu(@RequestBody @Valid MenuRequest request){
        return ApiResponse.<MenuResponse>builder()
                .result(menuService.createMenu(request))
                .build();
    }

    //Get All
    @GetMapping
    ApiResponse<List<MenuResponse>> findAll(){
        return ApiResponse.<List<MenuResponse>>builder()
                .result(menuService.getAllMenu())
                .build();
    }

    //Get by Id
    @GetMapping("/{menuId}")
    ApiResponse<MenuResponse> findById(@PathVariable("menuId") int id){
        return ApiResponse.<MenuResponse>builder()
                .result(menuService.getMenuById(id))
                .build();
    }

    //Update by Id
    @PutMapping("/{menuId}")
    ApiResponse<MenuResponse> updateMenu(@PathVariable("menuId") int id, @RequestBody MenuRequest request){
        return ApiResponse.<MenuResponse>builder()
                .result(menuService.updateMenu(id, request))
                .build();
    }

    //Delete by Id
    @DeleteMapping("/{menuId}")
    ApiResponse<String> deleteMenu(@PathVariable("menuId") int id){
        return ApiResponse.<String>builder()
                .result("Has been deleted !")
                .build();
    }
}
