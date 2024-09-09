package com.ordereart.OrderEat.service;

import com.ordereart.OrderEat.dto.request.MenuRequest;
import com.ordereart.OrderEat.dto.response.MenuResponse;
import com.ordereart.OrderEat.entity.Menu;
import com.ordereart.OrderEat.exception.AppException;
import com.ordereart.OrderEat.exception.ErrorCode;
import com.ordereart.OrderEat.mapper.MenuMapper;
import com.ordereart.OrderEat.repository.MenuRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuService {

    MenuRepository menuRepository;
    MenuMapper menuMapper;

    //Create
    public MenuResponse createMenu(MenuRequest request){
        Menu menu = menuMapper.toMenu(request);

        if (menuRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.EXISTS);
        }

        menu = menuRepository.save(menu);

        return menuMapper.toMenuResponse(menu);
    }

    //GetAll
    public List<MenuResponse> getAllMenu(){
        return menuRepository.findAll().stream().map(menuMapper::toMenuResponse).toList();
    }

    //GetId
    public MenuResponse getMenuById(int id){
        return menuMapper.toMenuResponse(menuRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND)));
    }

    //Update
    public MenuResponse updateMenu(int id, MenuRequest request){
        Menu menu = menuRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND));

        menuMapper.toMenuUpdate(menu, request);

        Menu menus = menuRepository.save(menu);
        return menuMapper.toMenuResponse(menus);
    }

    //Delete
    public void deleteMenu(int id){
        menuRepository.deleteById(id);
    }
}
