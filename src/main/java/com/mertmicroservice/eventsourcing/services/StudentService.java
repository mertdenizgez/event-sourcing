package com.mertmicroservice.eventsourcing.services;

import com.mertmicroservice.eventsourcing.events.StudentEvent;
import com.mertmicroservice.eventsourcing.models.Student;
import com.mertmicroservice.eventsourcing.repositories.StudentEventRepository;
import com.mertmicroservice.eventsourcing.repositories.StudentViewRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StudentService {

    private final StudentEventRepository studentEventRepository;
    private final StudentViewRepository studentViewRepository;

    public StudentService(StudentEventRepository studentEventRepository, StudentViewRepository studentViewRepository) {
        this.studentEventRepository = studentEventRepository;
        this.studentViewRepository = studentViewRepository;
    }

    public Mono<Student> getStudentView(String studentId) {
        return studentViewRepository.findByStudentId(studentId);
    }

    public Mono<Student> getStudent(String studentId) {
        return getEvents(studentId).collectList().flatMap(events -> Mono.just(createStudentView(events)));
    }

    private Student createStudentView(List<StudentEvent> events) {
        var student = new Student();
        for (var event : events) {
            student.Apply(event);
        }
        return student;
    }

    public Mono<Student> saveStudentView(Student student) {
        return studentViewRepository.save(student);
    }


    public Mono<StudentEvent> saveEvent(StudentEvent studentEvent) {
        return studentEventRepository.save(studentEvent).flatMap(sEvent -> getStudentView(sEvent.getStudentId()).switchIfEmpty(getEvents(sEvent.getStudentId()).collectList().flatMap(events -> {
            var student = createStudentView(events);
            return Mono.just(student);
        })).flatMap(studentView -> {
            studentView.Apply(sEvent);
            return saveStudentView(studentView).thenReturn(sEvent);
        }));
    }

    public Flux<StudentEvent> getEvents(String studentId) {
        return studentEventRepository.findAllByStudentId(studentId);
    }


}
