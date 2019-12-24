package com.fsd.taskmanager.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fsd.taskmanager.db.entiry.ParentTask;

public interface ParentTaskRepository extends MongoRepository<ParentTask, String> {

}
