package com.springacentesbmdeneme.Service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.springacentesbmdeneme.Repository.abstracts.VehicleBrandJpa;

import com.springacentesbmdeneme.Service.abstracts.VehicleBrandService;
import com.springacentesbmdeneme.entites.VehicleBrand;
@Service
public class VehicleBrandManager implements VehicleBrandService{
	@Autowired
	private VehicleBrandJpa vehicleBrandDao;

	
	@Override
	public void save(VehicleBrand vehicleBrand) {
		
		this.vehicleBrandDao.save(vehicleBrand);
	}
	@Override
	public List<VehicleBrand> getAll() {
		
		return vehicleBrandDao.findAll();
	}
}
