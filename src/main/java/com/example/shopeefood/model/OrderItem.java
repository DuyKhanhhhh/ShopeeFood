package com.example.shopeefood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "order_item")
public class OrderItem {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;



    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Long id,Product product, int quantity, Shop shop) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.shop = shop;

    }
}
