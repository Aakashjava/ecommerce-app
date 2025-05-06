package com.ecomNotification.ak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecomNotification.ak.service.NotificationService;

//NotificationController.java
@RestController
@RequestMapping("/notifications")
public class NotificationController {
 @Autowired
 private NotificationService notificationService;

// @PostMapping("/send")
// public ResponseEntity<String> sendNotification(@RequestParam String email, @RequestParam String message) {
//     notificationService.sendEmail(email, message);
//     return ResponseEntity.ok("Notification sent");
// }


 @PostMapping("/send")
 public void sendNotification(@RequestParam Long userId, @RequestParam String message) {
     notificationService.sendNotification(userId, message);
 }
 
}