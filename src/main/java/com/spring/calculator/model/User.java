package com.spring.calculator.model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;
    @Transient
    private String confirmPassword;

    public User() {
    }

    public User(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public User(Integer id, String email, String password, String confirmPassword) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
