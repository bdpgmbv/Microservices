package com.vyshali.notification_service.dto;

import com.vyshali.notification_service.domain.NotificationStatus;
import com.vyshali.notification_service.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    
    private String id;
    private NotificationType type;
    private String recipient;
    private String subject;
    private String message;
    private NotificationStatus status;
    private String orderId;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;
}
