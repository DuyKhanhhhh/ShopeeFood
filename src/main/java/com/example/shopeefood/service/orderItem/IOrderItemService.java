package com.example.shopeefood.service.orderItem;

import com.example.shopeefood.model.*;
import com.example.shopeefood.service.IGenerateService;

public interface IOrderItemService extends IGenerateService<OrderItem> {

    OrderItem findOrderItemByProduct(Product product);
    Iterable<OrderItem> findOrderItemByShop(Shop shop);
}
