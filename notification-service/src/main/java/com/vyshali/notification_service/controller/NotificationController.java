package com.vyshali.notification_service.controller;

import com.vyshali.notification_service.dto.NotificationRequest;
import com.vyshali.notification_service.dto.NotificationResponse;
import com.vyshali.notification_service.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Notification Management", description = "APIs for managing notifications")
public class NotificationController {
    
    private final NotificationService service;
    
    @PostMapping
    @Operation(summary = "Send a notification", description = "Sends an email/SMS/push notification")
    public ResponseEntity<NotificationResponse> sendNotification(@Valid @RequestBody NotificationRequest request) {
        log.info("REST request to send notification to: {}", request.getRecipient());
        NotificationResponse response = service.sendNotification(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping
    @Operation(summary = "Get all notifications", description = "Returns all notifications")
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        log.info("REST request to get all notifications");
        List<NotificationResponse> notifications = service.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }
    
    @GetMapping("/recipient/{recipient}")
    @Operation(summary = "Get notifications by recipient", description = "Returns notifications for a specific recipient")
    public ResponseEntity<List<NotificationResponse>> getByRecipient(@PathVariable String recipient) {
        log.info("REST request to get notifications for recipient: {}", recipient);
        List<NotificationResponse> notifications = service.getByRecipient(recipient);
        return ResponseEntity.ok(notifications);
    }
    
    @GetMapping("/order/{orderId}")
    @Operation(summary = "Get notifications by order", description = "Returns notifications for a specific order")
    public ResponseEntity<List<NotificationResponse>> getByOrderId(@PathVariable String orderId) {
        log.info("REST request to get notifications for order: {}", orderId);
        List<NotificationResponse> notifications = service.getByOrderId(orderId);
        return ResponseEntity.ok(notifications);
    }
}
