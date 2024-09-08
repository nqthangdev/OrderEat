package com.ordereart.OrderEat.controller;

import com.ordereart.OrderEat.dto.request.MenuRequest;
import com.ordereart.OrderEat.dto.response.ApiResponse;
import com.ordereart.OrderEat.entity.Menu;
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
    ApiResponse<Menu> createMenu(@RequestBody @Valid MenuRequest request){
        return ApiResponse.<Menu>builder()
                .result(menuService.createMenu(request))
                .build();
    }

    //Get All
    @GetMapping
    ApiResponse<List<Menu>> findAll(){
        return ApiResponse.<List<Menu>>builder()
                .result(menuService.getAllMenu())
                .build();
    }

    //Get Menu by Id
    @GetMapping("/{menuId}")
    ApiResponse<Menu> findById(@PathVariable("menuId") int id){
        return ApiResponse.<Menu>builder()
                .result(menuService.getMenuById(id))
                .build();
    }

    //Update Menu by Id
    @PutMapping("/{menuId}")
    ApiResponse<Menu> updateMenu(@PathVariable("menuId") int id, @RequestBody MenuRequest request){
        return ApiResponse.<Menu>builder()
                .result(menuService.updateMenu(id, request))
                .build();
    }

    //Delete Menu by Id
    @DeleteMapping("/{menuId}")
    ApiResponse<String> deleteMenu(@PathVariable("menuId") int id){
        return ApiResponse.<String>builder()
                .result("Has been deleted !")
                .build();
    }
}
