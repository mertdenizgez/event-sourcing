package com.mertmicroservice.eventsourcing.repositories;

import com.mertmicroservice.eventsourcing.events.StudentEvent;
import com.mertmicroservice.eventsourcing.models.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentViewRepository extends ReactiveMongoRepository<Student,String> {
    Mono<Student> findByStudentId(String studentId);
}
