package com.example.shopeefood.service.menu;

import com.example.shopeefood.model.Menu;
import com.example.shopeefood.model.Shop;
import com.example.shopeefood.service.IGenerateService;

public interface IMenuService extends IGenerateService<Menu> {
    Iterable<Menu> findAllByIdShop(Shop idShop);
}
