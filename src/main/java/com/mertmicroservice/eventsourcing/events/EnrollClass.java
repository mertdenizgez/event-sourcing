package com.mertmicroservice.eventsourcing.events;

import lombok.*;

import java.util.Date;


@Getter
@Setter
public class EnrollClass extends StudentEvent{
    String className;

    private EnrollClass(){
        super("",new Date().getTime(),EventType.ENROLL_CLASS);
    }

    public EnrollClass(String studentId, Long eventDate, String className){
        super(studentId,eventDate,EventType.ENROLL_CLASS);
        this.className = className;
    }
}
