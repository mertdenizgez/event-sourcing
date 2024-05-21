package com.mertmicroservice.eventsourcing.controllers;

import com.mertmicroservice.eventsourcing.events.CreateStudent;
import com.mertmicroservice.eventsourcing.events.EnrollClass;
import com.mertmicroservice.eventsourcing.events.StudentEvent;
import com.mertmicroservice.eventsourcing.models.Student;
import com.mertmicroservice.eventsourcing.services.StudentService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/event/student/")
public class StudentEventController {

    private final StudentService studentService;

    public StudentEventController(StudentService studentService){this.studentService=studentService;}

    @GetMapping("/view/{studentId}")
    public Mono<Student> getStudentView(@PathVariable("studentId") String studentId){
        return studentService.getStudentView(studentId);
    }

    @GetMapping("/{studentId}")
    public Mono<Student> getStudent(@PathVariable("studentId") String studentId){
        return studentService.getStudent(studentId);
    }

    @PostMapping("/create")
    public Mono<StudentEvent> createStudentEvent(@RequestBody() CreateStudent createStudent){
         return studentService.saveEvent(createStudent);
    }

    @PostMapping("/enroll")
    public Mono<StudentEvent> enrollClassEvent(@RequestBody() EnrollClass enrollClass){
         return studentService.saveEvent(enrollClass);
    }



}
