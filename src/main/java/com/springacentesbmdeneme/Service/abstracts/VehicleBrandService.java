package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.springacentesbmdeneme.Repository.abstracts.VehicleBrandJpa;
import com.springacentesbmdeneme.entites.VehicleBrand;


public interface VehicleBrandService {
	public void save(VehicleBrand vehicleBrand);
	List<VehicleBrand> getAll();
}
