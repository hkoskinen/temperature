package com.gravenium.temperature;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DisplayName("Temperature application tests")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TemperatureApplicationTests {

	@Test
	@DisplayName("Spring context loads properly")
	public void contextLoads() {
	}
}
