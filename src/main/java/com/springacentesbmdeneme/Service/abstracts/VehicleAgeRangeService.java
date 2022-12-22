package com.springacentesbmdeneme.Service.abstracts;

import java.util.Date;
import java.util.List;

import com.springacentesbmdeneme.entites.VehicleAgeRange;

public interface VehicleAgeRangeService {

	void save(VehicleAgeRange vehicleAgeRange);

	List<VehicleAgeRange> getAll();

	VehicleAgeRange choiceAgeRange(int year, Date date);

}
