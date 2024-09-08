package com.ordereart.OrderEat.service;

import com.ordereart.OrderEat.dto.request.TotalRequest;
import com.ordereart.OrderEat.dto.response.TotalResponse;
import com.ordereart.OrderEat.entity.Total;
import com.ordereart.OrderEat.entity.User;
import com.ordereart.OrderEat.exception.AppException;
import com.ordereart.OrderEat.exception.ErrorCode;
import com.ordereart.OrderEat.mapper.TotalMapper;
import com.ordereart.OrderEat.repository.MenuRepository;
import com.ordereart.OrderEat.repository.TotalRepository;
import com.ordereart.OrderEat.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TotalService {

    TotalMapper totalMapper;
    TotalRepository totalRepository;
    UserRepository userRepository;
    MenuRepository menuRepository;

    public TotalResponse create(TotalRequest request){
        User user = userRepository.findById(Integer.valueOf(request.getUserId()))
                                  .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND));

        Total total = totalMapper.toTotal(request);

//        Set<Integer> userId = request.getUserId().stream()
//                                     .map(Integer::valueOf)
//                                     .collect(Collectors.toSet());
//
//        List<User> users = userRepository.findAllById(userId);
//
//        for (Menu menu : menus) {
//            Total totals = new Total();
//            totals.setName(request.getName());
//            totals.setUser(user);
//            total = totalRepository.save(totals);
//        }

        total.setUser(user);

        total = totalRepository.save(total);

        return totalMapper.toTotalResponse(total);
    }

    public List<Total> getAlTotal(){
        return totalRepository.findAll()
                            .stream()
                            .map(totalMapper::toTotalDisplay)
                            .toList();
    }}
