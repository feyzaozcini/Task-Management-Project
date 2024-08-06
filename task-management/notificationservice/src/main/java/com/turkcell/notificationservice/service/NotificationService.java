package com.turkcell.notificationservice.service;


import com.turkcell.notificationservice.entity.Notification;
import com.turkcell.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void save(Notification notification) {
        Notification savedEntity = notificationRepository.save(notification);
        log.info("NotificationRepository.save saved Id: {}", savedEntity.getId());
    }
}
