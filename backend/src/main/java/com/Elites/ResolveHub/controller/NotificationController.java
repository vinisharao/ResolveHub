package com.Elites.ResolveHub.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Elites.ResolveHub.model.Notification;
import com.Elites.ResolveHub.service.NotificationService;
import com.Elites.ResolveHub.service.UserService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;

    public NotificationController(NotificationService notificationService,
                                  UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }
    @GetMapping
    public List<Notification> getNotifications(@RequestParam String email) {

        System.out.println("EMAIL RECEIVED: " + email);

        Long userId = userService.findByEmail(email).getId();
        return notificationService.getUserNotifications(userId);
    }
}