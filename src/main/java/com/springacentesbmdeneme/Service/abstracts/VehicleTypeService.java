package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import com.springacentesbmdeneme.entites.VehicleType;

public interface VehicleTypeService {

	void save(VehicleType vehicleType);

	List<VehicleType> getAll();

}
