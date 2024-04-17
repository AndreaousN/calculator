package com.spring.calculator.service;

import com.spring.calculator.model.Number;

import java.util.List;
import java.util.Optional;

public interface NumberService {
    List<Number> getAll();
    void save(Number number);
    Number getById(int id);
    void update(Number number);
    void delete(int id);
}
