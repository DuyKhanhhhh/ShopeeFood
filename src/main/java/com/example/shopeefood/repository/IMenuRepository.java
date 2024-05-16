package com.example.shopeefood.repository;

import com.example.shopeefood.model.Menu;
import com.example.shopeefood.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMenuRepository extends JpaRepository<Menu,Long> {
    Iterable<Menu> findAllByIdShop(Shop shop);
}
