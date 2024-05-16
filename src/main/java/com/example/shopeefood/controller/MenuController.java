package com.example.shopeefood.controller;

import com.example.shopeefood.model.Menu;
import com.example.shopeefood.model.Product;
import com.example.shopeefood.model.Shop;
import com.example.shopeefood.service.menu.IMenuService;
import com.example.shopeefood.service.shop.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("menus")
public class MenuController {
    @Autowired
    private IMenuService iMenuService;
    @Autowired
    private IShopService iShopService;
    @GetMapping()
    public ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu> menus = (List<Menu>) iMenuService.findAll();
        if (menus.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

}
