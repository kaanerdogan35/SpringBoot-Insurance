package com.springacentesbmdeneme.API;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springacentesbmdeneme.Service.abstracts.CareerService;
import com.springacentesbmdeneme.Service.abstracts.DiseaseService;
import com.springacentesbmdeneme.Service.abstracts.LifeService;
import com.springacentesbmdeneme.Service.abstracts.PersonAgeService;
import com.springacentesbmdeneme.Service.abstracts.PersonService;
import com.springacentesbmdeneme.Service.abstracts.ProposalService;
import com.springacentesbmdeneme.entites.Life;



@Controller
@RequestMapping("/life")
public class LifeController {
	@Autowired
	private LifeService lifeService;
	@Autowired
	private PersonService personService;
	@Autowired
	private ProposalService proposalService;
	@Autowired
	private CareerService careerService;
	@Autowired
	private DiseaseService diseaseService;
	@Autowired
	private PersonAgeService personAgeService;
	@GetMapping("/List")
	public String list(Model model) {
		model.addAttribute("lifes",lifeService.getAll());
		return "LifeList";
	}
	@GetMapping("/addLife/{id}")
	public String create(@PathVariable("id") Long id , Model model) {
		model.addAttribute("life", new Life());
		model.addAttribute("person", personService.getPersonByID(id));
		model.addAttribute("careers",careerService.getAll() );
		model.addAttribute("diseases", diseaseService.getAll());
		model.addAttribute("action", "life/add_life/"+id);
		return "LifeInsuranceCreatePage";
	}
	@PostMapping("add_life/{id}")
	public String save( @PathVariable("id") Long id , @ModelAttribute("life") Life life) {
			if(lifeService.getLifeByPersonId(life.getPerson().getId()).isEmpty()) {
				lifeService.lifeInsuranceCreate(life);
				return "redirect:/person/PersonActionPage/"+life.getPerson().getId();
			} 
			else return "redirect:/life/LifeInsuranceExistPage/"+life.getPerson().getLife().getId();
			
	} 
	@GetMapping("/LifeInsuranceExistPage/{id}")
	public String existCarInsurance( @PathVariable("id") Long id , Model model){
		Life life=lifeService.getLifeById(id);
		model.addAttribute("header","Life Insurances Already Exist");
		model.addAttribute("lifes",life);
		model.addAttribute("proposals",life.getProposal());
		return "LifeExist";
		
	}
	@GetMapping("/Detail/{id}")
	public String detail(@PathVariable("id") Long id , Model model) {
		model.addAttribute("header","");
		Life life=lifeService.getLifeById(id);
		if (life.getProposal().getEnd_date()!=null&&life.getProposal().getEnd_date().before(new Date())) {
			life.getProposal().setStatus("end");
			proposalService.save(life.getProposal());
		}
		
		model.addAttribute("lifes",life);
		model.addAttribute("proposals",life.getProposal());
		return "LifeExist";
	}
	@GetMapping("/Edit/{id}")
	public String edit(@PathVariable("id") Long id , Model model) {
		Life life=lifeService.getLifeById(id);
		model.addAttribute("life",life);
		model.addAttribute("person", life.getPerson());
		model.addAttribute("careers",life.getCareer() );
		model.addAttribute("diseases", diseaseService.getAll());
		model.addAttribute("personages", personAgeService.getAll());
		model.addAttribute("action", "life/_edit/"+id);
		return "EditLifePage";
	}
	@PostMapping("/_editjob/{id}")
	public String editJob(@PathVariable("id") Long id ,@ModelAttribute("life") Life life) {
		life.setId(id);	
		lifeService.lifeInsuranceCreate(life);	
		return "redirect:/person/PersonActionPage/"+life.getPerson().getId().toString();
	}
	@PostMapping("/_delete/{id}")
	public String deleteJob(@PathVariable("id") Long id){
		Life life=lifeService.getLifeById(id);
		lifeService.delete(id);
		return "redirect:/person/PersonActionPage/"+life.getPerson().getId();
	}
	
}
