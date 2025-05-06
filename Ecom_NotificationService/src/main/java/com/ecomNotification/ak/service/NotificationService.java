package com.ecomNotification.ak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ecomNotification.ak.Repo.NotificationRepository;
import com.ecomNotification.ak.entity.Notification;

//NotificationService.java
@Service
public class NotificationService {
// @Async
// public void sendEmail(String email, String message) {
//     // Simulate email sending
//     System.out.println("Sending email to " + email + ": " + message);
// }
 
 @Autowired
 private NotificationRepository notificationRepository;

 public void sendNotification(Long userId, String message) {
     Notification notification = new Notification();
     notification.setUserId(userId);
     notification.setMessage(message);
     notification.setStatus("SENT");
     notificationRepository.save(notification);
 }
}
