package com.turkcell.notificationservice.consumers.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime deadline;
    private List<Integer> userIds;
    private Integer projectId;
}
