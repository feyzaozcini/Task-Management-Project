package com.turkcell.notificationservice.entity;


import com.turkcell.common.events.KafkaTaskUpdateEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Getter
@Setter
@Document
@Builder
public class NotificationUpdate {
    @Id
    @GeneratedValue(strategy = UNIQUE)
    private String id;
    @Field
    private Integer taskId;
    @Field
    private String taskName;
    @Field
    private Boolean isSend;
    @Field
    private String description;
    @Field
    private LocalDateTime deadline;


    public static NotificationUpdate EventToNotificationEntity(KafkaTaskUpdateEvent event) {
        return NotificationUpdate.builder()
                .taskId(event.getId())
                .taskName(event.getTaskName())
                .description(event.getDescription())
                .deadline(event.getDeadline())
                .isSend(Boolean.TRUE)
                .build();
    }
}
