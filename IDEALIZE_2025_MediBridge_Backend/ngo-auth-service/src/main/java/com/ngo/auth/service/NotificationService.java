package com.ngo.auth.service;

import com.ngo.auth.entity.Notification;
import com.ngo.auth.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(String message, String type, String hospitalName, Integer donorId) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setType(type);
        notification.setHospitalName(hospitalName);
        notification.setDonorId(donorId);
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAllByOrderByCreatedAtDesc();
    }

    public void markAsRead(Integer id) {
        notificationRepository.markAsRead(id);
    }

    public Long getUnreadCount() {
        return notificationRepository.countByIsReadFalse();
    }
}