package com.springacentesbmdeneme.Service.concretes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.springacentesbmdeneme.Repository.abstracts.VehicleJpa;
import com.springacentesbmdeneme.Service.abstracts.BasePriceService;
import com.springacentesbmdeneme.Service.abstracts.ProposalService;
import com.springacentesbmdeneme.Service.abstracts.VehicleAgeRangeService;
import com.springacentesbmdeneme.Service.abstracts.VehicleService;
import com.springacentesbmdeneme.entites.Proposal;
import com.springacentesbmdeneme.entites.Vehicle;
import com.springacentesbmdeneme.entites.VehicleAgeRange;

@Service

public class VehicleManager implements VehicleService {
	@Autowired
	private VehicleJpa vehicleDao;
	@Autowired
	private ProposalService proposalService;
	@Autowired
	private VehicleAgeRangeService vehicleAgeRangeService;
	@Autowired
	private BasePriceService basePriceService;
	@Override
	public List<Vehicle> getAll() {
		
		return this.vehicleDao.findAll();
	}
	@Override
	public void save(Vehicle vehicle) {
	
		this.vehicleDao.save(vehicle);
	}
	@Override
	public Vehicle getVehicleById(Long id) {
	
		return this.vehicleDao.findById(id).get();
	}
	@Override
	public void vehicleInsuranceCreate(Vehicle vehicle) {
		List<Proposal> proposalList=vehicle.getProposal();
		proposalList.add(vehicleInsuranceCreateResponse(vehicle));
		vehicle.setProposal(proposalList);;
		vehicleDao.save(vehicle);
	}
	@Override
	public Proposal vehicleInsuranceCreateResponse(Vehicle vehicle) {
		Proposal proposal=new Proposal();
		Date date = new Date();
		proposal.setOrder_date(date);
		VehicleAgeRange vehicleAgeRange=vehicleAgeRangeService.choiceAgeRange(vehicle.getModelYear(), date);
		vehicle.setVehicleAgeRange(vehicleAgeRange);
		double priceCalculator=basePriceService.getByType("Vehicle").getInitialprice();
		priceCalculator*=vehicle.getVehicleAgeRange().getPrice_multiplier();
		priceCalculator*=vehicle.getVehicleBrand().getPrice_multiplier();
		priceCalculator*=vehicle.getVehicleType().getPrice_multiplier();
		priceCalculator = Math.round(priceCalculator*100.0)/100.0;
		proposal.setPrice(priceCalculator);
		proposalService.save(proposal);
		proposalService.WaitingProposalsDecline(vehicle.getProposal());
		return proposal;
	}
	@Override
	public String vehicleInsuranceExist(Long id) {
		return this.vehicleDao.vehicleProposalExist(id).toString();
	}
	@Override
	public void delete(Long id) {
		this.vehicleDao.deleteById(id);
		
	}
	@Override
	public Vehicle getVehicleByProposalId(Long id) {
		
		return this.vehicleDao.findById(this.vehicleDao.findVehicleIdByProposalId(id)).get();
	}
	@Override
	public List<Vehicle> getVehiclebyPersonId(Long id) {
		return this.vehicleDao.findVehiclebyPersonId(id);
	}
	@Override
	public Long getPersonIdByVehicleId(Long id) {
		
		return Long.valueOf(vehicleDao.findPersonIdByVehicleID(id).toString());
	}
}
