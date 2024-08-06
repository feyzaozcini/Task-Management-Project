package com.turkcell.taskservice.config.kafka.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;



@Configuration
@ConfigurationProperties(prefix = "kafka.topics.task-created")
@Getter
@Setter
public class TaskCreatedTopicProperties {
    private String topicName;
    private int partitionCount;
    private short replicationFactor;
    private String retentionInMs;
}
