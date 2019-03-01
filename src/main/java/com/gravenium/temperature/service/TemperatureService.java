package com.gravenium.temperature.service;

import com.gravenium.temperature.domain.Data;

public interface TemperatureService {
	public Data getTemperature(String city);
}
