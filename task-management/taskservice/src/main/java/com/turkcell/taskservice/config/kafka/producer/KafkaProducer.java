package com.turkcell.taskservice.config.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, Object payload, String messageKey) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, messageKey, payload);

        future.thenAccept(result -> {
            if (result == null) {
                log.info("Empty result on success for message with key {}", messageKey);
                return;
            }
            log.info("Message :{} published, topic : {}, partition : {} and offset : {}",
                    payload,
                    result.getRecordMetadata().topic(),
                    result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset());
        }).exceptionally(ex -> {
            log.error("Unable to deliver message to kafka", ex);
            return null;
        });
    }
}
