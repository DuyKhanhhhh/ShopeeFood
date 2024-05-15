package com.example.shopeefood.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product idProduct;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User idUser;
    private int quantity;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Cart(Product idProduct, User idUser, int quantity) {
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.quantity = quantity;
    }

    public Cart(long id, Product idProduct, User idUser, int quantity) {
        this.id = id;
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.quantity = quantity;
    }

    public Cart() {
    }
}
