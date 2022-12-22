package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import com.springacentesbmdeneme.entites.Proposal;
import com.springacentesbmdeneme.entites.Vehicle;

public interface VehicleService {

	List<Vehicle> getAll();

	void save(Vehicle vehicle);

	Vehicle getVehicleById(Long id);
	void vehicleInsuranceCreate(Vehicle vehicle);

	String vehicleInsuranceExist(Long id);

	void delete(Long id);

	Vehicle getVehicleByProposalId(Long id);

	List<Vehicle> getVehiclebyPersonId(Long id);
	Long getPersonIdByVehicleId(Long id);
	Proposal vehicleInsuranceCreateResponse(Vehicle vehicle);
}
