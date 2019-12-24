package com.fsd.taskmanager.exception;

import java.util.Date;

public class TaskResponse {

	private int httpStatus;
	private String errorMessage;
	private Date timestamp;
	private String details;

	public TaskResponse(int httpStatus, String errorMessage,String details) {
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.timestamp = new Date();
		this.details = details;
	}

	public TaskResponse(int httpStatus) {
		super();
		this.httpStatus = httpStatus;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getDetails() {
		return details;
	}

}
