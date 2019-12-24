package com.fsd.taskmanager.view.model;

public class Task {

	private String taskId;
	private String task;
	private String parentTaskId;
	private String parentTask;
	private String startDate;
	private String endDate;
	private Integer priority;
	private String taskStatus;

	public Task() {
	}

	public Task(String taskId, String task) {
		this.taskId = taskId;
		this.task = task;
	}

	public Task(String taskId, String parentTaskId, String parentTask, String task, String startDate, String endDate,
			Integer priority, String taskStatus) {
		super();
		this.taskId = taskId;
		this.parentTaskId = parentTaskId;
		this.parentTask = parentTask;
		this.task = task;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.taskStatus = taskStatus;
	}

	public Task(String taskId,String parentTaskId, String task, String startDate, String endDate, Integer priority, String taskStatus) {
		super();
		this.taskId = taskId;
		this.parentTaskId = parentTaskId;
		this.task = task;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.taskStatus = taskStatus;
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

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Override
	public String toString() {
		return "Task [" + (taskId != null ? "taskId=" + taskId + ", " : "")
				+ (parentTask != null ? "parentTask=" + parentTask + ", " : "")
				+ (task != null ? "task=" + task + ", " : "")
				+ (startDate != null ? "startDate=" + startDate + ", " : "")
				+ (endDate != null ? "endDate=" + endDate + ", " : "")
				+ (priority != null ? "priority=" + priority + ", " : "")
				+ (taskStatus != null ? "taskStatus=" + taskStatus : "") + "]";
	}

}
