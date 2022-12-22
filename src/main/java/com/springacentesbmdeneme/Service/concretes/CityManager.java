package com.springacentesbmdeneme.Service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.BuildAgeJpa;
import com.springacentesbmdeneme.Repository.abstracts.CityJpa;
import com.springacentesbmdeneme.Service.abstracts.CityService;
import com.springacentesbmdeneme.entites.BuildAge;
import com.springacentesbmdeneme.entites.City;

@Service
public class CityManager implements CityService {
	@Autowired
	private CityJpa cityDao;
	@Override
	public void save(City city) {
		this.cityDao.save(city);
		
	}

	@Override
	public List<City> getAll() {
		return this.cityDao.findAll();
	}
}
