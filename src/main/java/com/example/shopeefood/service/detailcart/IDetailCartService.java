package com.example.shopeefood.service.detailcart;
import com.example.shopeefood.model.*;
import com.example.shopeefood.service.IGenerateService;

import java.util.Optional;

public interface IDetailCartService extends IGenerateService<DetailCart> {
    Iterable<DetailCart> findAllByShopAndCart(Shop shop, User user);
    DetailCart findDetailCartByProductAndCart(Product product, Cart cart);
}
