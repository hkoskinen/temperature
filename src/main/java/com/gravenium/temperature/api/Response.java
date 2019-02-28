package com.gravenium.temperature.api;

import java.util.Map;

public class Response {
	private String status;
	private String message;
	
	private Map<String, String> data;
	private String error = null;
	
	public Response() {}
	
	public Response(String status, String message, Map<String, String> data, String error) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.error = error;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Map<String, String> getData() {
		return data;
	}
	
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
}