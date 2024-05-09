package com.example.shopeefood.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name = "shop")
@EntityListeners(AuditingEntityListener.class)
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String image;
    private Time timeStart;
    private Time timeEnd;
    @JoinColumn(name = "city_id")
    @ManyToOne
    private City idCity;
    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category idCategory;
    @JoinColumn(name = "users_id")
    @ManyToOne
    private User idUser;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Time getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }

    public Time getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }

    public City getIdCity() {
        return idCity;
    }

    public void setIdCity(City idCity) {
        this.idCity = idCity;
    }

    public Category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Category idCategory) {
        this.idCategory = idCategory;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Shop() {
    }

    public Shop(Long id, String name, String phoneNumber, String email, String image, Time timeStart, Time timeEnd, City idCity, Category idCategory, User idUser) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.image = image;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.idCity = idCity;
        this.idCategory = idCategory;
        this.idUser = idUser;
    }

    public Shop(String name, String phoneNumber, String email, String image, Time timeStart, Time timeEnd, City idCity, Category idCategory, User idUser) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.image = image;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.idCity = idCity;
        this.idCategory = idCategory;
        this.idUser = idUser;
    }
}
