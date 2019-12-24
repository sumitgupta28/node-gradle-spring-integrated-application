package com.fsd.taskmanager.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fsd.taskmanager.common.TaskStatusEnum;
import com.fsd.taskmanager.db.entiry.TaskDetail;
import com.fsd.taskmanager.db.repository.TaskDetailRepository;
import com.fsd.taskmanager.exception.ResourceNotFoundException;
import com.fsd.taskmanager.view.model.Task;
import com.fsd.taskmanager.view.transformer.TaskTransformer;

@Component
public class TaskManagerService {

	
	private static final Logger LOGGER = LogManager.getLogger(TaskManagerService.class);


	@Autowired
	private TaskDetailRepository taskDetailRepository;

	@Autowired
	private TaskTransformer taskTransformer;

	/**
	 * This method is used to create a task.
	 * 
	 * @param task
	 */
	public void createTask(Task task) {
		// convert task into task detail
		TaskDetail taskDetail = taskTransformer.transformTaskModelToTaskEntity(task);
		LOGGER.debug(" Creating new Task [] " , taskDetail);
		// Store task Details
		taskDetail = taskDetailRepository.save(taskDetail);
		// Store parent and child Task
		/*
		 * Optional.ofNullable(taskDetail.getParentTaskId()) .ifPresent(e ->
		 * parentTaskRepository.save(new ParentTask(task.getTaskId(), e)));
		 */
	}

	/**
	 * This method is used to pull all the task from database.
	 * 
	 * @return List<Task>
	 */
	public List<Task> findAllTaskWithDetail() {
		List<TaskDetail> taskDetails = taskDetailRepository.findAll();
		// Collect all the Task into map
		Map<String, String> taskDetailsMap = taskDetails.stream()
				.collect(Collectors.toMap(TaskDetail::getTaskId, TaskDetail::getTask));

		// Map DB Entity TaskDetail to Task View
		List<Task> tasks = taskDetails.stream().map(temp -> {
			Task task = new Task(temp.getTaskId(),temp.getParentTaskId(), temp.getTask(), temp.getStartDate(), temp.getEndDate(),
					temp.getPriority(), temp.getTaskStatus());
			Optional.ofNullable(temp.getParentTaskId()).ifPresent(e -> {
				task.setParentTask(taskDetailsMap.get(e));
			});
			return task;
		}).collect(Collectors.toList());
		return tasks;
	}

	/**
	 * This method is used to pull all the task & task name from database.
	 * 
	 * @return List<Task>
	 */
	public List<Task> findAllTaskWithTaskIdAndName() {
		List<TaskDetail> taskDetails = taskDetailRepository.findAll();
		// Map DB Entity TaskDetail to Task View
		List<Task> tasks = taskDetails.stream().map(temp -> {
			return new Task(temp.getTaskId(), temp.getTask());
		}).collect(Collectors.toList());
		return tasks;
	}

	/**
	 * Find TaskDetail by provided TaskId and convert into Task.
	 * 
	 * @param taskId
	 * @return Task
	 * @throws ResourceNotFoundException
	 */
	public Task findTaskByTaskId(String taskId) throws ResourceNotFoundException {
		TaskDetail temp = getTaskById(taskId);
		 Task task = new Task(temp.getTaskId(), temp.getParentTaskId(), temp.getTask(),
				temp.getStartDate(), temp.getEndDate(), temp.getPriority(), temp.getTaskStatus());
		if(Optional.ofNullable(temp.getParentTaskId()).isPresent()){
			task.setParentTask(getTaskById(temp.getParentTaskId()).getTask());
		}
		return task;
	}

	/**
	 * Find TaskDetail by provided TaskId and Delete It.
	 * 
	 * @param taskId
	 * @throws ResourceNotFoundException
	 */
	public void deleteTaskByTaskId(String taskId) throws ResourceNotFoundException {
		TaskDetail temp = getTaskById(taskId);
		taskDetailRepository.deleteByTaskId(temp.getTaskId());
	}

	/**
	 * This method is used to update the task as completed.
	 * 
	 * @param task
	 * @throws ResourceNotFoundException
	 */
	public void completeTask(Task task) throws ResourceNotFoundException {
		TaskDetail taskDetail = getTaskById(task.getTaskId());
		taskDetail.setTaskStatus(TaskStatusEnum.COMPLETED.name());
		taskDetail=taskDetailRepository.save(taskDetail);
	}

	/**
	 * This method is used to update the task
	 * 
	 * @param task
	 * @throws ResourceNotFoundException
	 */
	public void updateTask(@Valid Task task) throws ResourceNotFoundException {
		TaskDetail taskDetail = getTaskById(task.getTaskId());
		taskDetail.setTask(task.getTask());
		taskDetail.setPriority(task.getPriority());
		taskDetail.setParentTaskId(task.getParentTaskId());
		taskDetail.setStartDate(task.getStartDate());
		taskDetail.setEndDate(task.getEndDate());
		taskDetailRepository.save(taskDetail);
	}

	/**
	 * This method is used to return the taskDetail for given Task Id.
	 * 
	 * @param taskId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private TaskDetail getTaskById(String taskId) throws ResourceNotFoundException {
		TaskDetail taskDetail = taskDetailRepository.findByTaskId(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found for this task :: " + taskId));
		return taskDetail;
	}

}
