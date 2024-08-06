package com.turkcell.notificationservice.entity;

import com.turkcell.notificationservice.consumers.model.TaskCreatedEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Getter
@Setter
@Document
@Builder

public class Notification {
    @Id
    @GeneratedValue(strategy = UNIQUE)
    private String id;
    @Field
    private Integer taskId;
    @Field
    private String taskName;
    @Field
    private Boolean isSend;

    public static Notification EventToNotificationEntity(TaskCreatedEvent event) {
        return Notification.builder()
                .taskId(event.getId())
                .taskName(event.getTaskName())
                .isSend(Boolean.TRUE)
                .build();
    }

}
