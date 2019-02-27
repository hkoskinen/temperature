package com.gravenium.temperature.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class TemperatureController {

	@Value("${app.weather.api.key}")
	private String API_KEY;
	
	private RestTemplate rest = new RestTemplate();

	@GetMapping(value = "/temperatures", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getTemperatureByCity(@RequestParam(required = false) String city) {
		if (city == null)
			return "Invalid city name";
		return makeRequest(city);
	}

	private String makeRequest(String city) {
		String result;
		try {
			result = rest.getForObject("https://api.openweathermap.org/data/2.5/weather?q=" + city
					+ "&units=metric&appid=" + API_KEY, String.class);
		} catch (HttpClientErrorException e) {
			ApiError er = new ApiError("Invalid city name");
			result = er.getMessage();
		}
		return result;
	}
}

class ApiError {
	private String message;

	public ApiError(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
