package com.springacentesbmdeneme.API;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springacentesbmdeneme.Service.abstracts.PersonService;
import com.springacentesbmdeneme.Service.abstracts.ProposalService;
import com.springacentesbmdeneme.Service.abstracts.VehicleBrandService;
import com.springacentesbmdeneme.Service.abstracts.VehicleService;
import com.springacentesbmdeneme.Service.abstracts.VehicleTypeService;
import com.springacentesbmdeneme.entites.Proposal;
import com.springacentesbmdeneme.entites.Vehicle;



@Controller
@RequestMapping("/vehicle")
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;
	@Autowired 
	private PersonService personService;
	@Autowired
	private ProposalService proposalService;
	@Autowired
	private VehicleBrandService vehicleBrandService;
	@Autowired
	private VehicleTypeService vehicleTypeService;
	@GetMapping("/vehicleList")
	public String VehicleList(Model model){
		model.addAttribute("vehicles", vehicleService.getAll());
		return "VehicleList";
	}
	@GetMapping("addVehicle/{id}")
	public String addVehicle(@PathVariable("id") Long id , Model model) {
		model.addAttribute("vehicle", new Vehicle());
		model.addAttribute("persons", personService.getPersonByID(id));
		model.addAttribute("vehicleTypes",vehicleTypeService.getAll());
		model.addAttribute("vehicleBrands",vehicleBrandService.getAll());
		model.addAttribute("action", "vehicle/add_vehicle");
		return "VehicleCreatePage";
	}
	@PostMapping("/add_vehicle")
	public String save(@ModelAttribute("vehicle") Vehicle vehicle){
		vehicleService.save(vehicle);
		return "redirect:/person/personList";
	}
	@GetMapping("/InsuranceCreate/{id}")
	public String vehicleInsuranceSave(@PathVariable("id") Long id , Model model) {
		Vehicle vehicle=vehicleService.getVehicleById(id);
		if(!proposalService.getProposalsIdByVehicleId(vehicle.getId()).isEmpty()) {
			if(proposalService.checkProposalStatusAcceptedorWaiting(proposalService.getProposalsIdByVehicleId(vehicle.getId()))) {
				return "redirect:/vehicle/VehicleExistInsurance/"+Long.toString(id);
			}		
		}
		vehicleService.vehicleInsuranceCreate(vehicle);
		return "redirect:/vehicle/Detail/"+Long.toString(vehicle.getId());
	}
	@GetMapping("/VehicleExistInsurance/{id}")
	public String existVehicleInsurance(@PathVariable("id") Long id , Model model){
		Vehicle vehicle=vehicleService.getVehicleById(id);
		model.addAttribute("header", "Insurance Propose Already Exist");
		model.addAttribute("vehicles",vehicle);
		model.addAttribute("proposals",vehicle.getProposal());
	    return "VehicleInsuranceExistPage";
	}
	@GetMapping("/Detail/{id}")
	public String detail(@PathVariable("id") Long id , Model model) {
		
		Vehicle vehicle=vehicleService.getVehicleById(id);
		for(Proposal proposal:vehicle.getProposal()) {
			if (proposal.getEnd_date()!=null && proposal.getEnd_date().before(new Date())) {
				proposal.setStatus("end");
				proposalService.save(proposal);
			}
		}
		model.addAttribute("header","");
		model.addAttribute("vehicles",vehicle);
		model.addAttribute("proposals",vehicle.getProposal());
		return "VehicleInsuranceExistPage";
	}
	@GetMapping("/Edit/{id}")
	public String editCar(@PathVariable("id") Long id , Model model) {
		Vehicle vehicle=vehicleService.getVehicleById(id);
		model.addAttribute("vehicle",vehicle);
		model.addAttribute("persons",personService.getPersonByID(vehicle.getPerson().getId()));
		model.addAttribute("vehicleBrands",vehicle.getVehicleBrand());
		model.addAttribute("vehicleTypes",vehicle.getVehicleType());
		model.addAttribute("action","vehicle/_editjob/"+id.toString());
		return "VehicleEditPage";
	}
	@PostMapping("/_editjob/{id}")
	public String edit(@PathVariable("id") Long id , @ModelAttribute("vehicle") Vehicle vehicle) {
		vehicle.setId(id);
		vehicle.setVehicleBrand(vehicleService.getVehicleById(id).getVehicleBrand());
		vehicle.setVehicleType(vehicleService.getVehicleById(id).getVehicleType());
		vehicleService.save(vehicle);
		return "redirect:/person/PersonActionPage/"+vehicleService.getPersonIdByVehicleId(id);
		
	}
	@PostMapping("/_delete/{id}")
	public String delete(@PathVariable("id") Long id)
	{	
		String redirectUrl=vehicleService.getPersonIdByVehicleId(id).toString();
		vehicleService.delete(id);
		return "redirect:/person/PersonActionPage/"+redirectUrl;
	}
}
