package com.example.shopeefood.service;

import java.util.Optional;

public interface IG <T>{
    Iterable<T> findAll();
    T save(T t);
    void remote(T t);
    T delete(Long id);
    Optional<T> findById(Long id);

}
