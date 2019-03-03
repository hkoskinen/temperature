package com.gravenium.temperature.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gravenium.temperature.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
	Optional<City> findByName(String name);
}
