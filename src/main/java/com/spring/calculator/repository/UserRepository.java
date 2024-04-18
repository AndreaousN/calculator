package com.spring.calculator.repository;

import com.spring.calculator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // nera butina rasyti query jeigu mathc'ina User objekto reiksme (field'a)
    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    @Query("select u from User u where u.id = ?1")
    User findById(int id);
}
