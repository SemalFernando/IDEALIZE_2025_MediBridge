package com.ngo.auth.controller;

import com.ngo.auth.entity.Notification;
import com.ngo.auth.service.NotificationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public Notification createNotification(@RequestBody Map<String, Object> payload) {
        String message = (String) payload.get("message");
        String type = (String) payload.get("type");
        String hospitalName = (String) payload.get("hospitalName");
        Integer donorId = (Integer) payload.get("donorId");

        return notificationService.createNotification(message, type, hospitalName, donorId);
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @PatchMapping("/{id}/read")
    public void markAsRead(@PathVariable Integer id) {
        notificationService.markAsRead(id);
    }

    @GetMapping("/unread-count")
    public Long getUnreadCount() {
        return notificationService.getUnreadCount();
    }
}