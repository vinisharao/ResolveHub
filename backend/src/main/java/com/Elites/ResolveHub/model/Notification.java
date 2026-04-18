package com.Elites.ResolveHub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String message;

    private boolean isRead = false;

    // 🔹 Default Constructor
    public Notification() {
    }

    // 🔹 Parameterized Constructor
    public Notification(Long userId, String message) {
        this.userId = userId;
        this.message = message;
        this.isRead = false;
    }

    // 🔹 Getters and Setters

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}