package com.vyshali.notification_service.domain;

public enum NotificationType {
    EMAIL("Email Notification"),
    SMS("SMS Notification"),
    PUSH("Push Notification");
    
    private final String description;
    
    NotificationType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
