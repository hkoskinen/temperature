package com.gravenium.temperature.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
class TemperatureController {
	private static final Logger log = LoggerFactory.getLogger(TemperatureController.class);
	
	private final TemperatureService temperatureService;
	private final CityRepository cityRepository;
	
	public TemperatureController(TemperatureService temperatureService, CityRepository cityRepository) {
		this.temperatureService = temperatureService;
		this.cityRepository = cityRepository;
	}

	@GetMapping(value = "/temperatures/{city}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Get current temperature by city name")
	public ResponseEntity<Object> getTemperatureByCity(@PathVariable String city) {
		Data d = temperatureService.getTemperature(city);
		if (d != null)
			return ResponseEntity.ok(new Response("200", "success", d, null));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new Response("404", "failure", null, "Temperature not found for given city name"));
		
	}

	@PostMapping(value = "/cities", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Save favorite city")
	public ResponseEntity<Object> saveFavoriteCity(@Valid @RequestBody CityResource cityResource) {
		// first make a query to weather api, so we know we have a proper city name
		Data d = temperatureService.getTemperature(cityResource.getName());
		if (d != null) {
			// we have proper city name, then check if we already have saved the city name
			Optional<City> c = cityRepository.findByName(d.getName());
			if (!c.isPresent()) {
				// if not, save the city to database
				City savedCity = cityRepository.save(new City(d.getName()));
				
				URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				        .buildAndExpand(savedCity.getId()).toUri();
				
				return ResponseEntity.created(location).build();
			} else {
				// we already have a city with the given name
				log.debug("We have already stored city with name " + c.get().getName());
				
			}
		} else {
			// city name is invalid because the weather api doesn't recognize it, bad request?
			log.debug("Bad city name " + cityResource.getName());
			// status code should be number, success should be plain boolean
			return ResponseEntity.badRequest().body(new Response("400", "failure", null, "Please provide valid city name"));
		}
		return ResponseEntity.ok(cityResource.getName());
	}

	@GetMapping(value = "/cities/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Get current temperature by stored city id")
	public ResponseEntity<Object> getTemperatureById(@PathVariable Long id) {
		// first try to find city by id from database
		Optional<City> c = cityRepository.findById(id);
		if (c.isPresent()) {
			// we found record by id, so we can get the name and get the current weather
			Data d = temperatureService.getTemperature(c.get().getName());
			
			return ResponseEntity.ok(new Response("200", "success", d, null));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new Response("404", "failure", null, "Temperature not found for given city id"));
	}
}

class CityResource {
	@NotBlank(message = "Name is required")
	private String name;
	
	public CityResource() {}
	public CityResource(@NotBlank(message = "Name is required") String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}