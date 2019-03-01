package com.gravenium.temperature.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gravenium.temperature.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

}
