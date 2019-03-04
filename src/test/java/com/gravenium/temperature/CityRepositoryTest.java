package com.gravenium.temperature;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gravenium.temperature.entity.City;
import com.gravenium.temperature.repository.CityRepository;

@DisplayName("City repository tests")
@ExtendWith(SpringExtension.class)
@DataJpaTest
class CityRepositoryTest {

	@Autowired
	private CityRepository cityRepository;

	@Test
	@DisplayName("When city is saved to database, it should be found with the same city name")
	void whenSaved_thenFindsByName() {
		cityRepository.save(new City("Tampere"));
		assertThat(cityRepository.findByName("Tampere"), notNullValue());
	}
	
	@Test
	@DisplayName("When city is saved to database, it should be found with id")
	void whenSaved_thenFindsById() {
		cityRepository.save(new City("Tampere"));
		assertThat(cityRepository.findById(1L), notNullValue());
	}
}
