package com.gravenium.temperature.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gravenium.temperature.domain.Data;

public class Response {
	private String status;
	private String message;
	
	@JsonInclude(Include.NON_NULL)
	private Data data;
	
	@JsonInclude(Include.NON_NULL)
	private String error = null;
	
	public Response() {}
	
	public Response(String status, String message, Data data, String error) {
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
	
	public Data getData() {
		return data;
	}
	
	public void setData(Data data) {
		this.data = data;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
}