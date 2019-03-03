package com.gravenium.temperature.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

	private Double temperature;
	
	@JsonProperty("name")
	private String name;
	
	private String country;
	
	@JsonProperty("main")
	private void getTemperatureFromMainObject(Map<String, Object> main) {
		this.temperature = (Double)main.get("temp");
	}
	@JsonProperty("sys")
	private void getCountryFromSysObject(Map<String, Object> sys) {
		this.country = (String)sys.get("country");
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}