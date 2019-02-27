package com.gravenium.temperature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	public String getTemperatureByCity(@RequestParam(required = false) String city) {
		if (city == null)
			return "Invalid city name";
		
		return tempService.getTemperature(city);
	}
}
