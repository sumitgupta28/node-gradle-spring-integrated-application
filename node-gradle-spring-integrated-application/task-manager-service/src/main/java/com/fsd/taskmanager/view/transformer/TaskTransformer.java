package com.fsd.taskmanager.view.transformer;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fsd.taskmanager.common.TaskStatusEnum;
import com.fsd.taskmanager.db.entiry.TaskDetail;
import com.fsd.taskmanager.view.model.Task;

@Component
public class TaskTransformer {

	/**
	 * this method generate Task DB entity from Task View model.
	 * @param task
	 * @return
	 */
	public TaskDetail transformTaskModelToTaskEntity(Task task)
	{
		// generating unique taskId
		final String taskId = UUID.randomUUID().toString().replace("-", "");
		TaskDetail taskDetail = new TaskDetail();
		taskDetail.setTaskId(taskId);
		taskDetail.setTaskStatus(TaskStatusEnum.IN_PROGRESS.name());
	
		taskDetail.setPriority(task.getPriority());
		taskDetail.setStartDate(task.getStartDate());
		taskDetail.setEndDate(task.getEndDate());
		taskDetail.setTask(task.getTask());
		Optional.ofNullable(task.getParentTaskId()).ifPresent(e -> taskDetail.setParentTaskId(e));
		
		return taskDetail;
	}
}
