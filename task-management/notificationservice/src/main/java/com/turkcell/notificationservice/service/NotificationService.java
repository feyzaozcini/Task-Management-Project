package com.turkcell.notificationservice.service;


import com.turkcell.notificationservice.entity.Notification;
import com.turkcell.notificationservice.entity.NotificationUpdate;
import com.turkcell.notificationservice.repository.NotificationRepository;
import com.turkcell.notificationservice.repository.NotificationUpdatedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationUpdatedRepository notificationUpdatedRepository;

    public void save(Notification notification) {
        Notification savedEntity = notificationRepository.save(notification);
        log.info("NotificationRepository.save saved Id: {}", savedEntity.getId());
    }

    public void save(NotificationUpdate notification) {
        NotificationUpdate savedEntity = notificationUpdatedRepository.save(notification);
        log.info("NotificationRepository.save saved Id: {}", savedEntity.getId());
    }


}
