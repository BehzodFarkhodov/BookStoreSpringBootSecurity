package org.example.bookstorespringbootsecurity.service;

import org.example.bookstorespringbootsecurity.domain.OrderCreatedDTO;
import org.example.bookstorespringbootsecurity.entity.OrderEntity;
import org.example.bookstorespringbootsecurity.entity.UserEntity;
import org.example.bookstorespringbootsecurity.repository.OrderRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;


    public void buy(OrderCreatedDTO orderCreatedDTO, Principal principal) {
        UserEntity user = userService.findByUsername(principal.getName());
        orderCreatedDTO.setOwnerId(user.getId());
        orderCreatedDTO.setPrice(orderCreatedDTO.getPrice());
        orderCreatedDTO.setAmount(1);
        OrderEntity map = modelMapper.map(orderCreatedDTO, OrderEntity.class);
        orderRepo.save(map);
    }

    public List<OrderEntity> getAllOrders(Principal principal) {
        UserEntity user = userService.findByUsername(principal.getName());
        return orderRepo.getAllByOwnerId(user.getId());
    }


}
