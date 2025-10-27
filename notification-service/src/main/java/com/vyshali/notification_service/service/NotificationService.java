package com.vyshali.notification_service.service;

import com.vyshali.notification_service.domain.Notification;
import com.vyshali.notification_service.domain.NotificationStatus;
import com.vyshali.notification_service.dto.NotificationRequest;
import com.vyshali.notification_service.dto.NotificationResponse;
import com.vyshali.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    
    private final NotificationRepository repository;
    
    public NotificationResponse sendNotification(NotificationRequest request) {
        log.info("Sending {} notification to: {}", request.getType(), request.getRecipient());
        
        Notification notification = new Notification();
        notification.setType(request.getType());
        notification.setRecipient(request.getRecipient());
        notification.setSubject(request.getSubject());
        notification.setMessage(request.getMessage());
        notification.setOrderId(request.getOrderId());
        notification.setStatus(NotificationStatus.SENT);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setSentAt(LocalDateTime.now());
        notification.setRetryCount(0);
        
        Notification saved = repository.save(notification);
        log.info("Notification sent successfully with ID: {}", saved.getId());
        
        return mapToResponse(saved);
    }
    
    public List<NotificationResponse> getAllNotifications() {
        log.info("Fetching all notifications");
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public List<NotificationResponse> getByRecipient(String recipient) {
        log.info("Fetching notifications for recipient: {}", recipient);
        return repository.findByRecipient(recipient).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public List<NotificationResponse> getByOrderId(String orderId) {
        log.info("Fetching notifications for order: {}", orderId);
        return repository.findByOrderId(orderId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    private NotificationResponse mapToResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .type(notification.getType())
                .recipient(notification.getRecipient())
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .status(notification.getStatus())
                .orderId(notification.getOrderId())
                .createdAt(notification.getCreatedAt())
                .sentAt(notification.getSentAt())
                .build();
    }
}
