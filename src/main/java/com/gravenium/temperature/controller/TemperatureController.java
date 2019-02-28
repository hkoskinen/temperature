package com.gravenium.temperature.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gravenium.temperature.api.Response;
import com.gravenium.temperature.service.TemperatureService;

@RestController
@RequestMapping("/api")
public class TemperatureController {

	@Autowired
	private TemperatureService temperatureService;

	@GetMapping(value = "/temperatures")
	public ResponseEntity<Response> getTemperatureByCity(@RequestParam(required = false) String city) {
		
		if (city == null || city.length() == 0) {
			Map<String, String> data = new HashMap<>();
			Response resp = new Response("400", "failure", data, "Missing city name");
			return ResponseEntity.badRequest().body(resp);
		}
		
		Map<String, String> data = new HashMap<>();
		String temperatureResponse = temperatureService.getTemperature(city);
		
		if (temperatureResponse != null) {
			data.put("temperature", temperatureResponse);
			Response resp = new Response("200", "success", data, null);
			return ResponseEntity.ok().body(resp);
		} else {
			Response resp = new Response("404", "failure", data, "Invalid city name");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
		}
	}
}