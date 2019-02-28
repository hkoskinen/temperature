package com.gravenium.temperature.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.gravenium.temperature.domain.Temperature;

@Service
class TemperatureServiceImpl implements TemperatureService {
private static final Logger log = LoggerFactory.getLogger(TemperatureService.class);
	
	@Value("${app.openweathermap.api.key}")
	private String API_KEY;
	
	private RestTemplate rest = new RestTemplate();
	
	public String getTemperature(String city) {
		String result;
		try {
			Temperature temp = rest.getForObject("https://api.openweathermap.org/data/2.5/weather?q=" + city
					+ "&units=metric&appid=" + API_KEY, Temperature.class);
			
			log.debug("Temperature in " + city + " is " + temp.getMain().get("temp") + "°C");
			result = temp.getMain().get("temp");
			
		} catch (HttpClientErrorException e) {
			result = null;
		}
		return result;
	}
}
