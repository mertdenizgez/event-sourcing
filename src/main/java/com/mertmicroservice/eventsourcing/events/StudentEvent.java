package com.mertmicroservice.eventsourcing.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class StudentEvent {
    String studentId;
    Long eventDate;
    EventType eventType;

    private StudentEvent() {

    }

    public StudentEvent(String studentId, Long eventDate, EventType eventType) {
        this.studentId = studentId;
        this.eventDate = eventDate;
        this.eventType = eventType;
    }
}
