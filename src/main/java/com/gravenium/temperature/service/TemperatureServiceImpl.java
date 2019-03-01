package com.gravenium.temperature.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.gravenium.temperature.domain.Data;

@Service
class TemperatureServiceImpl implements TemperatureService {
	
	private static final Logger log = LoggerFactory.getLogger(TemperatureService.class);
	
	@Value("${app.openweathermap.api.key}")
	private String API_KEY;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public Data getTemperature(String city) {
		Data temp;
		try {
			temp = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?q=" + city
					+ "&units=metric&appid=" + API_KEY, Data.class);
			
			log.debug("Temperature in " 
					+ temp.getCityName() 
					+ "," 
					+ temp.getCountry() 
					+ " is " 
					+ temp.getTemperature() 
					+ "°C");
			
		} catch (HttpClientErrorException e) {
			temp = null;
		}
		return temp;
	}
}
