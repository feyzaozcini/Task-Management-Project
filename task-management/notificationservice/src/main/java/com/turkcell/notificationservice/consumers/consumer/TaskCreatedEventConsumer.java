package com.turkcell.notificationservice.consumers.consumer;

import com.turkcell.notificationservice.consumers.model.TaskCreatedEvent;
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
    public void consumeCreatedTaskEvent(@Payload TaskCreatedEvent eventData,
                                        @Headers ConsumerRecord<String, Object> consumerRecord) {
        try {
            log.info("Received TaskCreatedEvent: {} from partition: {} with offset: {}",
                    eventData, consumerRecord.partition(), consumerRecord.offset());

            Notification entity = Notification.EventToNotificationEntity(eventData);
            notificationService.save(entity);

            log.info("Successfully processed TaskCreatedEvent for message key: {}",
                    consumerRecord.key());

        } catch (Exception e) {
            log.error("Error processing TaskCreatedEvent", e);
        }
    }
}
