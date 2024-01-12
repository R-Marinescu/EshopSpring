package com.company.eshop.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "lastName")
    private String lastName;

    public User(Integer userId, String username, String lastName) {
        this.userId = userId;
        this.username = username;
        this.lastName = lastName;
    }

    public User() {

    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Id
    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, lastName);
    }

    @Override
    public String toString() {
        return "user{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
