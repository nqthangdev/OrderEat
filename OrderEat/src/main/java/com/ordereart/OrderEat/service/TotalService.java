package com.ordereart.OrderEat.service;

import com.ordereart.OrderEat.dto.request.TotalRequest;
import com.ordereart.OrderEat.entity.Total;
import com.ordereart.OrderEat.entity.User;
import com.ordereart.OrderEat.exception.AppException;
import com.ordereart.OrderEat.exception.ErrorCode;
import com.ordereart.OrderEat.mapper.TotalMapper;
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

    public Total create(TotalRequest request){

        Total total = totalMapper.toTotal(request);

        Set<Integer> userId = request.getUserId().stream()
                                     .map(Integer::valueOf)
                                     .collect(Collectors.toSet());

        List<User> users = userRepository.findAllById(userId);

        for (User user : users) {
            Total totals = new Total();
            totals.setName(request.getName());
            totals.setUser(user);
            total = totalRepository.save(totals);
        }

        total = totalRepository.save(total);

        return totalMapper.toTotalDisplay(total);
    }

    public List<Total> getAlTotal(){
        return totalRepository.findAll()
                            .stream()
                            .map(totalMapper::toTotalDisplay)
                            .toList();
    }

    public Total getTotalById(int id){
        return totalMapper.toTotalDisplay(totalRepository.findById(id)
                        .orElseThrow(()-> new AppException(ErrorCode.NOTFOUND)));
    }

    public void deleteTotal(int id){
        totalRepository.deleteById(id);
    }

}
