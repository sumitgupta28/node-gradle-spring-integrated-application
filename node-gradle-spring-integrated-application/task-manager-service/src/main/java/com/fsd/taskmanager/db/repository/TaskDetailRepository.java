package com.fsd.taskmanager.db.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fsd.taskmanager.db.entiry.TaskDetail;

public interface TaskDetailRepository extends MongoRepository<TaskDetail, String> {

	Optional<TaskDetail> findByTaskId(String taskId);

	void deleteByTaskId(String taskId);

}
