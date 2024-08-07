package com.turkcell.notificationservice.consumers.model;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCreatedEvent {
    private Integer id;
    private String taskName;
    private String description;
    private Integer projectId;
}
