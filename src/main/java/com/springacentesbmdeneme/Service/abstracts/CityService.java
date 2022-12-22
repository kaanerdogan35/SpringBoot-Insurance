package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import com.springacentesbmdeneme.entites.City;

public interface CityService {

	void save(City city);

	List<City> getAll();

}
