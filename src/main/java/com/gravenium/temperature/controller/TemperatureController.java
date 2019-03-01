package com.gravenium.temperature.controller;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gravenium.temperature.api.Response;
import com.gravenium.temperature.domain.Data;
import com.gravenium.temperature.entity.City;
import com.gravenium.temperature.repository.CityRepository;
import com.gravenium.temperature.service.TemperatureService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class TemperatureController {

	private static final Logger log = LoggerFactory.getLogger(TemperatureController.class);
	
	private TemperatureService temperatureService;
	private CityRepository cityRepository;
	
	public TemperatureController(TemperatureService temperatureService, CityRepository cityRepository) {
		this.temperatureService = temperatureService;
		this.cityRepository = cityRepository;
	}

	@GetMapping(value = "/temperatures", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Get current temperature by city name")
	public ResponseEntity<Response> getTemperatureByCity(@RequestParam(required = false) String city) {
		
		if (city == null || city.length() == 0) {
			Response resp = new Response("400", "failure", null, "Missing city name");
			return ResponseEntity.badRequest().body(resp);
		}
		
		Data temperatureResponse = temperatureService.getTemperature(city);
		
		if (temperatureResponse != null) {
			Response resp = new Response("200", "success", temperatureResponse, null);
			return ResponseEntity.ok().body(resp);
		} else {
			Response resp = new Response("404", "failure", null, "Invalid city name");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
		}
	}
	
	@PostMapping(value = "/cities", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Save favorite city")
	public ResponseEntity<?> saveFavoriteCity(@RequestBody Map<String ,String> body) {
		String city = body.get("city");
		if (city != null && city.length() > 0) {
			Data temperatureResponse = temperatureService.getTemperature(city);
			if (temperatureResponse != null) {
				
				City c = cityRepository.save(new City(temperatureResponse.getCityName()));
				
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				        .buildAndExpand(c.getId()).toUri();
				return ResponseEntity.created(location).build();	
			}
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "/cities/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Get current temperature by stored city id")
	public ResponseEntity<?> getTemperatureById(@PathVariable Long id) {
		log.debug("Trying to find city by id " + id);
		
		Optional<City> c = cityRepository.findById(id);
		if (c.isPresent()) {
			Data temperatureResponse = temperatureService.getTemperature(c.get().getName());
			Response resp = new Response("200", "success", temperatureResponse, null);
			return ResponseEntity.ok().body(resp);
		} else {
			Response resp = new Response("404", "failure", null, "Invalid city id");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
		}
	}
}