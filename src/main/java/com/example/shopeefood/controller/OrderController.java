package com.example.shopeefood.controller;

import com.example.shopeefood.model.*;
import com.example.shopeefood.repository.IDetailCartRepository;
import com.example.shopeefood.repository.IOrderItemRepository;
import com.example.shopeefood.repository.IOrderRepository;
import com.example.shopeefood.service.detailcart.IDetailCartService;
import com.example.shopeefood.service.orderItem.IOrderItemService;
import com.example.shopeefood.service.shop.IShopService;
import com.example.shopeefood.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IShopService iShopService;
    @Autowired
    private IOrderItemService iOrderItemService;
    @Autowired
    private IOrderRepository iOrderRepository;
    @Autowired
    private IOrderItemRepository iOrderItemRepository;
    @Autowired
    private IDetailCartService iDetailCartService;
    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
         List<Order> orders = iOrderRepository.findByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

        @PostMapping("/{idUser}/{idShop}")
    public ResponseEntity<Order> createOrder(@PathVariable long idShop, @PathVariable long idUser) {

        Optional<User> userOptional = iUserService.findById(idUser);
        Optional<Shop> shopOptional = iShopService.findById(idShop);
        if (!userOptional.isPresent() || !shopOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        User user = userOptional.get();
        Shop shop = shopOptional.get();

        List<OrderItem> orderItems =  iOrderItemRepository.findByShopId(shop.getId());
        Order order = new Order();
        order.setUser(user); ;
        for (OrderItem item : orderItems) {
            if(item.getOrder()==null){
                order.addOrderItem(item);
            }
        }
        Order savedOrder = iOrderRepository.save(order);
        for (OrderItem orderItem : orderItems){
            iDetailCartService.remove(orderItem.getId());
        }
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

}
