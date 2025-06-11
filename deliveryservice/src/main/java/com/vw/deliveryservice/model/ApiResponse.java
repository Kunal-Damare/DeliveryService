package com.vw.deliveryservice.model;

public class ApiResponse {
	
	String Status;
	String Message;
	public ApiResponse(String status, String message) {
		super();
		Status = status;
		Message = message;
	}
	public String getStatus() {
		return Status;
	}
	public String getMessage() {
		return Message;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public void setMessage(String message) {
		Message = message;
	}

}
