package com.springacentesbmdeneme.Service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.NumberofFloorsJpa;
import com.springacentesbmdeneme.Service.abstracts.NumberofFloorsService;
import com.springacentesbmdeneme.entites.NumberofFloors;

@Service
public class NumberofFloorsManager implements NumberofFloorsService{
	@Autowired
	private NumberofFloorsJpa floorDao;
	
	@Override
	public void save(NumberofFloors floor) {
		this.floorDao.save(floor);
	}
	
	@Override
	public List<NumberofFloors> getAll(){
		return this.floorDao.findAll();
	}
}
