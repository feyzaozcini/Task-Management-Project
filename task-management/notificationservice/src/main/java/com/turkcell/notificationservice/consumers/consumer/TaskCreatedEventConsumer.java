package com.turkcell.notificationservice.consumers.consumer;


import com.turkcell.common.events.KafkaTaskEvent;
import com.turkcell.notificationservice.entity.Notification;
import com.turkcell.notificationservice.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskCreatedEventConsumer {
    private final NotificationService notificationService;

    @KafkaListener(topics = "${kafka.topics.task-created.topic}",
            groupId = "${kafka.topics.task-created.consumerGroup}",
            containerFactory = "concurrentKafkaListenerContainerFactory"
    )
    public void consumeCreatedTaskEvent(@Payload KafkaTaskEvent eventData,
                                        @Headers ConsumerRecord<String, Object> consumerRecord) {
        try {


            Notification entity = Notification.EventToNotificationEntity(eventData);
            notificationService.save(entity);

            // Konsola basmak için
            log.info("Message Content: {}", eventData);


        } catch (Exception e) {
            log.error("Error processing KafkaTaskEvent", e);
        }
    }
}
