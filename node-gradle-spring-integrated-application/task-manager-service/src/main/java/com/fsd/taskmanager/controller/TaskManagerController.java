package com.fsd.taskmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.taskmanager.exception.ResourceNotFoundException;
import com.fsd.taskmanager.exception.TaskResponse;
import com.fsd.taskmanager.service.TaskManagerService;
import com.fsd.taskmanager.view.model.Task;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@RestController
@RequestMapping("/v1")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Task.")
public class TaskManagerController {

	
	private static final Logger LOGGER = LogManager.getLogger(TaskManagerController.class);
	 
	@Autowired
	private TaskManagerService taskManagerService;

	@RequestMapping(method = RequestMethod.POST, path = "/task", produces = "application/json")
	@ApiOperation("Create a new Task.")
	public TaskResponse createTask(@Valid @RequestBody Task task) {
		LOGGER.info(" Creating New task {} " , task);
		taskManagerService.createTask(task);
		return new TaskResponse(HttpStatus.CREATED.value());
	}

	@RequestMapping(method = RequestMethod.PATCH, path = "/task/complete-task/{taskId}", produces = "application/json")
	@ApiOperation("Update Task as Completed.")
	public TaskResponse completeTask(@PathVariable(value = "taskId") String taskId,@Valid @RequestBody Task task) throws ResourceNotFoundException {
		task.setTaskId(taskId);
		LOGGER.info(" updating task as completed {} " , task);
		taskManagerService.completeTask(task);
		return new TaskResponse(HttpStatus.OK.value());
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/task/{taskId}")
	@ApiOperation("Update Task.")
	public ResponseEntity<Task> updateTask(@PathVariable(value = "taskId") String taskId,
			@Valid @RequestBody Task task) throws ResourceNotFoundException {
		LOGGER.info(" updating task  {} " , task);
		taskManagerService.updateTask(task);
		return ResponseEntity.ok(task);
	}


	@RequestMapping(method = RequestMethod.GET, path = "/task", produces = "application/json")
	@ApiOperation("Returns list of all Task in the system.")
	public List<Task> getAllTask() {
		List<Task> taskList = taskManagerService.findAllTaskWithDetail();
		LOGGER.info(" All Task List {} " , taskList);
		return taskList;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/task/minimal", produces = "application/json")
	@ApiOperation("Returns list of all Task in the system.")
	public List<Task> findAllTaskWithTaskIdAndName() {
		List<Task> taskList = taskManagerService.findAllTaskWithTaskIdAndName();
		LOGGER.info(" All Task List {} " , taskList);
		return taskList;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/task/{taskId}", produces = "application/json")
	@ApiOperation("Returns a specific Task by their task Id. 404 if does not exist.")
	public ResponseEntity<Task> getTaskById(
			@ApiParam("task Id of the task to be obtained. Cannot be empty.") @PathVariable(value = "taskId") String taskId)
			throws ResourceNotFoundException {
		Task task = taskManagerService.findTaskByTaskId(taskId);
		LOGGER.info(" getTaskById - Task {} " , task);
		return ResponseEntity.ok().body(task);
	}

	
	@RequestMapping(method = RequestMethod.DELETE, path = "/task/{taskId}")
	@ApiOperation("Delete a specific Task by their task Id. 404 if does not exist.")
	public ResponseEntity<TaskResponse> deleteTask(
			@ApiParam("task Id of the task to be deleted. Cannot be empty..") @PathVariable(value = "taskId") String taskId)
			throws ResourceNotFoundException {
		taskManagerService.deleteTaskByTaskId(taskId);
		return ResponseEntity.ok(new TaskResponse(HttpStatus.OK.value()));
	}

}
