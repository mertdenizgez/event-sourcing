package com.mertmicroservice.eventsourcing.repositories;

import com.mertmicroservice.eventsourcing.events.StudentEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StudentEventRepository extends ReactiveMongoRepository<StudentEvent,String> {
    Flux<StudentEvent> findAllByStudentId(String studentId);
}
