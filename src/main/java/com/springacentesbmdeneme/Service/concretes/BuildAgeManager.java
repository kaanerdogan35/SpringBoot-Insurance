package com.springacentesbmdeneme.Service.concretes;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.BuildAgeJpa;
import com.springacentesbmdeneme.Service.abstracts.BuildAgeService;
import com.springacentesbmdeneme.entites.BuildAge;
import com.springacentesbmdeneme.entites.Person;
import com.springacentesbmdeneme.entites.VehicleAgeRange;
@Service
public class BuildAgeManager implements BuildAgeService {
	@Autowired
	private BuildAgeJpa buildAgeDao;
	@Override
	public void save(BuildAge buildAge) {
		this.buildAgeDao.save(buildAge);
		
	}

	@Override
	public List<BuildAge> getAll() {
		return this.buildAgeDao.findAll();
	}
	
}
