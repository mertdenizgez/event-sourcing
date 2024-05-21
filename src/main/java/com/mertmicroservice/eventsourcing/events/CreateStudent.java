package com.mertmicroservice.eventsourcing.events;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateStudent extends StudentEvent{
    String name;
    String email;

    private CreateStudent(){
        super("",new Date().getTime(), EventType.CREATE_STUDENT);
    }

    public CreateStudent(String studentId, Long eventDate, String name, String email){
        super(studentId, eventDate, EventType.CREATE_STUDENT);
        this.name = name;
        this.email = email;
    }
}
