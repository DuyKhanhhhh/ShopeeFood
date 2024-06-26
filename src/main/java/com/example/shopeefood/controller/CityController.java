package com.example.shopeefood.controller;


import com.example.shopeefood.model.Category;
import com.example.shopeefood.model.City;
import com.example.shopeefood.service.category.ICategoryService;
import com.example.shopeefood.service.city.ICityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private ICityService iCityService;

    @GetMapping()
    public ResponseEntity<List<City>> getAllMenus() {
        List<City> cityList = (List<City>) iCityService.findAll();
        if (cityList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cityList, HttpStatus.OK);

    }
}
