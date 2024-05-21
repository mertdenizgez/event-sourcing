package com.mertmicroservice.eventsourcing.events;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateStudent extends StudentEvent{
    String email;
    String name;

    private UpdateStudent(){
        super("",new Date().getTime(),EventType.UPDATE_STUDENT);
    }

    public UpdateStudent(String studentId, Long eventDate, String name, String email){
        super(studentId,eventDate,EventType.UPDATE_STUDENT);
        this.name = name;
        this.email = email;
    }
}
