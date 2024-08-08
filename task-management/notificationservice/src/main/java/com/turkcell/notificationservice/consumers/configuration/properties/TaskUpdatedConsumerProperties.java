package com.turkcell.notificationservice.consumers.configuration.properties;



import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka.topics.task-updated")
@Getter
@Setter
public class TaskUpdatedConsumerProperties {
    private String topic;
    private String consumerGroup;
}
