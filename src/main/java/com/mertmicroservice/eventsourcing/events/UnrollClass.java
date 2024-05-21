package com.mertmicroservice.eventsourcing.events;

import lombok.*;

import java.util.Date;


@Getter
@Setter
public class UnrollClass extends StudentEvent {
    String className;

    private UnrollClass(){
        super("",new Date().getTime(),EventType.UNROLL_CLASS);
    }

    public UnrollClass(String studentId, Long eventDate, String className){
        super(studentId,eventDate,EventType.UNROLL_CLASS);
        this.className = className;
    }
}
