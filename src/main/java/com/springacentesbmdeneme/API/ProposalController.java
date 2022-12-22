package com.springacentesbmdeneme.API;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springacentesbmdeneme.Service.abstracts.DaskService;
import com.springacentesbmdeneme.Service.abstracts.LifeService;
import com.springacentesbmdeneme.Service.abstracts.ProposalService;
import com.springacentesbmdeneme.Service.abstracts.VehicleService;
import com.springacentesbmdeneme.entites.Proposal;

@Controller
@RequestMapping("/proposal")
public class ProposalController {
	@Autowired
	private ProposalService proposalService;
	@Autowired
	private DaskService daskService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired 
	private LifeService lifeService;
	@GetMapping("/ProposalList")
	public String proposalList(Model model) {
		for(Proposal proposal:proposalService.getAll()) {
			if (proposal.getEnd_date()!=null&& proposal.getEnd_date().before(new Date())) {
				proposal.setStatus("end");
				proposalService.save(proposal);
			}
		}
		model.addAttribute("proposals", proposalService.getAll());
		return "ProposalList";
	}
	@PostMapping("/accept/{type}/{id}")
	public String proposalAccept(@PathVariable("proposalId") Long id , @PathVariable("type") String type){
		 
		Proposal proposal=proposalService.getProposalById(id);
		proposalService.proposalAccept(proposal);
		if(type.equals("dask")) {
		return "redirect:/"+type+"/Detail/"+daskService.getDaskByProposalId(id).getId().toString();
		}
		else if (type.equals("vehicle")) {return "redirect:/"+type+"/Detail/"+vehicleService.getVehicleByProposalId(id).getId().toString();
		}
		else if (type.equals("life")) {return "redirect:/"+type+"/Detail/"+lifeService.getLifeByProposalId(id).getId().toString();
		}
		return "redirect:/33";
		
	}
	@PostMapping("/decline/{id}")
	public String proposalDecline(@PathVariable("id") Long id){
		Proposal proposal=proposalService.getProposalById(id);
		proposalService.decline(proposal);
		return "redirect:/person/personList";
	}
	@PostMapping("/cancel/{id}")
	public String proposalCancel(@PathVariable("id") Long id) {
		Proposal proposal=proposalService.getProposalById(id);
		proposalService.cancel(proposal);
		return "redirect:/person/personList";
	}
	
}
