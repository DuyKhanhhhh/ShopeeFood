package com.example.shopeefood.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seat_code")
public class SeatCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
