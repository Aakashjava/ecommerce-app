package com.ecomNotification.ak.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomNotification.ak.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}