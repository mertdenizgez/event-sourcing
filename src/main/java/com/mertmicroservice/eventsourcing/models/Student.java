package com.mertmicroservice.eventsourcing.models;

import com.mertmicroservice.eventsourcing.events.*;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
@Getter
@Setter
public class Student {

    ObjectId _id;
    String studentId;
    String name;
    String email;
    Set<String> classes;

    public void Apply(StudentEvent studentEvent){

        if(studentEvent == null){
            return;
        }

        if(studentEvent instanceof CreateStudent createStudent){
            Apply(createStudent);
        }
        else if(studentEvent instanceof UpdateStudent updateStudent){
            Apply(updateStudent);
        }
        else if(studentEvent instanceof EnrollClass enrollClass){
            Apply(enrollClass);
        }
        else if(studentEvent instanceof UnrollClass unenrollClass){
            Apply(unenrollClass);
        }

    }

    private void Apply(CreateStudent createStudent){
        this.studentId = createStudent.getStudentId();
        this.name = createStudent.getName();
        this.email = createStudent.getEmail();
    }

    private void Apply(UpdateStudent updateStudent){
        this.name = updateStudent.getName();
        this.email = updateStudent.getEmail();
    }

    private void Apply(EnrollClass enrollClass){
        if(this.classes == null){
            this.classes = new HashSet<>();
        }

        this.classes.add(enrollClass.getClassName());
    }

    private void Apply(UnrollClass unrollClass){
        if(this.classes == null || this.classes.isEmpty()){
            return;
        }

        this.classes.remove(unrollClass.getClassName());
    }
}
