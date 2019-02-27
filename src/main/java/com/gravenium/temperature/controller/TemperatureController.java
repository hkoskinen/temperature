package com.gravenium.temperature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gravenium.temperature.service.TemperatureService;

@RestController
@RequestMapping("/api")
public class TemperatureController {

	@Autowired
	private TemperatureService tempService;

	@GetMapping(value = "/temperatures", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Response> getTemperatureByCity(@RequestParam(required = false) String city) {
		if (city == null || city.length() == 0)
			return ResponseEntity.badRequest().body(new Response("400", "Missing city name",""));
		return ResponseEntity.ok(new Response("200", "success", tempService.getTemperature(city)));
	}
}

class Response {
	private String status;
	private String message;
	private String data;

	public Response(String status, String message, String data) {
		this.status = status;
		this.message = message;
		this.data = data;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
