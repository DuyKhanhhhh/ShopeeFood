package com.example.shopeefood.repository;

import com.example.shopeefood.model.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface IOrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findByShopId(Long shopId);

    OrderItem findOrderItemByProduct(Product product);

}
