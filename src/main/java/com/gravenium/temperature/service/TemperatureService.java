package com.gravenium.temperature.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.gravenium.temperature.api.ApiError;

@Service
public class TemperatureService {
	
	@Value("${app.weather.api.key}")
	private String API_KEY;
	
	private RestTemplate rest = new RestTemplate();
	
	public String getTemperature(String city) {
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
