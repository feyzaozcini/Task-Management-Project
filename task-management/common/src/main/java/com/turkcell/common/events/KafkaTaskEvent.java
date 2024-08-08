package com.turkcell.common.events;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaTaskEvent {
    private Integer id;
    private String taskName;
    private String description;

    private LocalDateTime startDate;
    private LocalDateTime deadline;
}
