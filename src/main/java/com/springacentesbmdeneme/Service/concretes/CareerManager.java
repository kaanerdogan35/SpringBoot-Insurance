package com.springacentesbmdeneme.Service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.CareerJpa;
import com.springacentesbmdeneme.Service.abstracts.CareerService;
import com.springacentesbmdeneme.entites.Career;



@Service
public class CareerManager implements CareerService{
	@Autowired
	private CareerJpa careerDao;

	@Override
	public void save(Career career) {
		this.careerDao.save(career);
		
	}

	@Override
	public List<Career> getAll() {
		return this.careerDao.findAll();
	}

}
