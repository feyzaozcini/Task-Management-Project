package com.turkcell.notificationservice.consumers.consumer;

import com.turkcell.common.events.KafkaTaskUpdateEvent;
import com.turkcell.notificationservice.entity.NotificationUpdate;
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
public class TaskUpdatedEventConsumer {
    private final NotificationService notificationService;

    @KafkaListener(topics = "${kafka.topics.task-updated.topic}",
            groupId = "${kafka.topics.task-updated.consumerGroup}",
            containerFactory = "concurrentKafkaListenerContainerFactory"
    )
    public void consumeCreatedTaskEvent(@Payload KafkaTaskUpdateEvent eventData,
                                        @Headers ConsumerRecord<String, Object> consumerRecord) {
        try {


            NotificationUpdate entity = NotificationUpdate.EventToNotificationEntity(eventData);
            notificationService.save(entity);

            // Konsola basmak için
            log.info("Message Content: {}", eventData);


        } catch (Exception e) {
            log.error("Error processing KafkaTaskEvent", e);
        }
    }
}
