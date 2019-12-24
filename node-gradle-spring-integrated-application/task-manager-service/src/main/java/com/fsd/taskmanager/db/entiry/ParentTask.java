package com.fsd.taskmanager.db.entiry;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parent_task")
public class ParentTask {
	@Indexed(unique = true)
	private String taskId;
	private String parentTaskId;

	public ParentTask() {
	}

	public ParentTask(String taskId, String parentTaskId) {
		super();
		this.taskId = taskId;
		this.parentTaskId = parentTaskId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

}
