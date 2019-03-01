package com.gravenium.temperature.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

	private Double temperature;
	
	@JsonProperty("name")
	private String cityName;
	
	private String countryName;
	
	@JsonProperty("main")
	private void doesthismatter(Map<String, Object> main) {
		this.temperature = (Double)main.get("temp");
	}
	@JsonProperty("sys")
	private void doesthismatteragain(Map<String, Object> sys) {
		this.countryName = (String)sys.get("country");
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public String getCityName() {
		return cityName;
	}

	public void setName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountry() {
		return countryName;
	}

	public void setCountry(String countryName) {
		this.countryName = countryName;
	}
}