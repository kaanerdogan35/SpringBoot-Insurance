package com.springacentesbmdeneme.Service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.VehicleBrandJpa;
import com.springacentesbmdeneme.Repository.abstracts.VehicleTypeJpa;
import com.springacentesbmdeneme.Service.abstracts.VehicleTypeService;
import com.springacentesbmdeneme.entites.VehicleBrand;
import com.springacentesbmdeneme.entites.VehicleType;
@Service
public class VehicleTypeManager implements VehicleTypeService{
	@Autowired
	private VehicleTypeJpa vehicleTypeDao;

	
	@Override
	public void save(VehicleType vehicleType) {
		
		this.vehicleTypeDao.save(vehicleType);
	}
	@Override
	public List<VehicleType> getAll() {
		
		return vehicleTypeDao.findAll();
	}
}
