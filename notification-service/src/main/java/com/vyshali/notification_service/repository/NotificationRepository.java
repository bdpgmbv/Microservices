package com.vyshali.notification_service.repository;

import com.vyshali.notification_service.domain.Notification;
import com.vyshali.notification_service.domain.NotificationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    
    List<Notification> findByRecipient(String recipient);
    
    List<Notification> findByStatus(NotificationStatus status);
    
    List<Notification> findByOrderId(String orderId);
}
