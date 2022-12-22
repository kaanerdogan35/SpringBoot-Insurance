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

import com.springacentesbmdeneme.Service.abstracts.AreaService;
import com.springacentesbmdeneme.Service.abstracts.BuildAgeService;
import com.springacentesbmdeneme.Service.abstracts.BuildTypeService;
import com.springacentesbmdeneme.Service.abstracts.CityService;
import com.springacentesbmdeneme.Service.abstracts.DaskService;
import com.springacentesbmdeneme.Service.abstracts.NumberofFloorsService;
import com.springacentesbmdeneme.Service.abstracts.PersonService;
import com.springacentesbmdeneme.Service.abstracts.ProposalService;
import com.springacentesbmdeneme.entites.Dask;
import com.springacentesbmdeneme.entites.Proposal;



@Controller
@RequestMapping("/dask")
public class DaskController {
	@Autowired
	private DaskService daskService;
	@Autowired
	private PersonService personService;
	@Autowired
	private CityService cityService;
	@Autowired
	private BuildTypeService buildTypeService;
	@Autowired
	private BuildAgeService buildAgeService;
	@Autowired 
	private ProposalService proposalService;
	@Autowired
	private AreaService areaService;
	@Autowired 
	private NumberofFloorsService floorService;
	@GetMapping("/daskList")
	public String daskList(Model model){
		model.addAttribute("dasks", daskService.getAll());
		return "DaskList";
	}
	@PostMapping("/add_dask")
	public String save(@ModelAttribute("dask") Dask dask){
		daskService.save(dask);
		return "redirect:/person/personList";
	}
	@GetMapping("/addDask/{id}")
	public String addDask(@PathVariable("id") Long id , Model model) {
		model.addAttribute("dask",new Dask());
		model.addAttribute("persons",personService.getPersonByID(id));
		model.addAttribute("cities",cityService.getAll());
		model.addAttribute("buildtypes",buildTypeService.getAll());
		model.addAttribute("areas",areaService.getAll());
		model.addAttribute("floors",floorService.getAll());
		model.addAttribute("buildages",buildAgeService.getAll());
		model.addAttribute("action", "dask/add_dask");
		return "DaskCreatePage";
	}
	@GetMapping("/InsuranceCreate/{id}")
	public String daskInsuranceSave(@PathVariable("id") Long id , Model model) {
		Dask dask=daskService.getDaskById(id);
		if(!dask.getDaskproposal().isEmpty()) {
			if(proposalService.checkProposalStatusAcceptedorWaiting(proposalService.getProposalsIdByDaskId(dask.getId()))) {
				return "redirect:/dask/DaskInsuranceExistPage/"+Long.toString(id);
			}
		}
		daskService.daskInsuranceCreate(dask);
		return "redirect:/dask/Detail/"+Long.toString(id);
	}
	@GetMapping("/DaskInsuranceExistPage/{id}")
	public String daskExist(@PathVariable("id") Long id , Model model) {
		Dask dask=daskService.getDaskById(id);
		model.addAttribute("header","DASK INSURANCE ALREADY EXIST");
		model.addAttribute("dasks",dask);
		model.addAttribute("proposals",dask.getDaskproposal());
		return "DaskExistPage";
	}
	@GetMapping("/Detail/{id}")
	public String detail(@PathVariable("id") Long id , Model model) {
		model.addAttribute("header","");
		Dask dask=daskService.getDaskById(id);
		for(Proposal proposal:dask.getDaskproposal()) {
			if (proposal.getEnd_date()!=null&& proposal.getEnd_date().before(new Date())) {
				proposal.setStatus("end");
				proposalService.save(proposal);
			}
		}
		model.addAttribute("dasks",dask);
		model.addAttribute("proposals",dask.getDaskproposal());
		return "DaskExistPage";
	}
	@GetMapping("/Edit/{id}")
	public String DaskEdit(@PathVariable("id") Long id , Model model){
		Dask dask=daskService.getDaskById(id);
		model.addAttribute("dask",dask);
		model.addAttribute("persons",personService.getPersonByID(dask.getPerson().getId()));
		model.addAttribute("cities",dask.getCity());
		model.addAttribute("buildtypes",dask.getBuildtype());
		model.addAttribute("buildages",buildAgeService.getAll());
		model.addAttribute("areas",areaService.getAll());
		model.addAttribute("floors",floorService.getAll());
		model.addAttribute("action", "dask/_editjob/"+dask.getId().toString());
		
		return "DaskEdit";
	}
	@PostMapping("/_editjob/{id}")
	public String daskEditJob(@PathVariable("id") Long id , @ModelAttribute("dask") Dask dask){
		daskService.update( dask);
		return "redirect:/person/PersonActionPage/"+daskService.getPersonIdByDaskId(id);	
	}
	@PostMapping("/_delete/{id}")
	public String delete(@PathVariable("id") Long id)
	{
		String redirectUrl=daskService.getPersonIdByDaskId(id).toString();
		daskService.delete(id);
		return "redirect:/person/PersonActionPage/"+redirectUrl;
	}
}
