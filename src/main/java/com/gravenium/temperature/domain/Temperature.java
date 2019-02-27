package com.gravenium.temperature.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Temperature {

	@JsonProperty
	private Map<String, String> main;

	public Temperature() {}

	public Map<String, String> getMain() {
		return main;
	}

	public void setMain(Map<String, String> main) {
		this.main = main;
	}
}