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
    public void consumeCreatedUserEvent(@Payload TaskCreatedEvent eventData,
                                        @Headers ConsumerRecord<String, Object> consumerRecord) {
        log.info("UserCreatedEventConsumer.consumeApprovalRequestResultedEvent consumed EVENT :{} " +
                        "from partition : {} " +
                        "with offset : {} " +
                        "thread : {} " +
                        "for message key: {}",
                eventData, consumerRecord.partition(), consumerRecord.offset(), Thread.currentThread().getName(), consumerRecord.key());

        Notification entity = Notification.EventToNotificationEntity(eventData);

        notificationService.save(entity);

    }
}
