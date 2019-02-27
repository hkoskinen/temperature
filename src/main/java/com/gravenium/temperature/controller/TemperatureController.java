package com.gravenium.temperature.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureController {

	@GetMapping("/temperatures")
	public String getTemperatures() {
		return "List of all temperaturs";
	}
	
	@GetMapping("/temperatures/{id}")
	public String getTemperature(@PathVariable("id") long id) {
		return "Temperature for a city with id " + id;
	}
}
