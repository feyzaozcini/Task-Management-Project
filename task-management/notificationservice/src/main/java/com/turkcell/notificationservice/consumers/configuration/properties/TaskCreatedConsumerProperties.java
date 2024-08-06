package com.turkcell.notificationservice.consumers.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka.topics.task-created")
@Getter
@Setter
public class TaskCreatedConsumerProperties {
    private String topic;
    private String consumerGroup;
}
