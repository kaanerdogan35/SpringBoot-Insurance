package com.springacentesbmdeneme.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springacentesbmdeneme.Service.abstracts.DaskService;
import com.springacentesbmdeneme.Service.abstracts.LifeService;
import com.springacentesbmdeneme.Service.abstracts.PersonService;
import com.springacentesbmdeneme.Service.abstracts.VehicleService;
import com.springacentesbmdeneme.entites.Person;
import javax.validation.Valid;
@Controller
@RequestMapping("/person")
public class PersonController {
	@Autowired 
	private PersonService personService;
	@Autowired 
	private VehicleService vehicleService;
	@Autowired
	private DaskService daskService;
	@Autowired
	private LifeService lifeService;
	
	@GetMapping("/personList")
	public String personList(Model model){
		model.addAttribute("persons", personService.getAll());
		return "PersonList";
	}
	
	

	@GetMapping("/PersonCreate")
	public String personadd(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("action", "person/person_create");
		return "PersonCreatePage";
	}
	@PostMapping("/person_create")
	public String save(@Valid @ModelAttribute("person") Person person){
		
		if(personService.PersonExistCheck(person.getTc())=="0") {
			personService.save(person);
			return "redirect:/person/personList";
		}
		else return "redirect:/person/personExist/"+personService.getPersonByTc(person.getTc()).getId().toString();
	}
	@GetMapping("personExist/{id}")
	public String existPerson(@PathVariable("id") Long id , Model model) {
		model.addAttribute("persons",personService.getPersonByID(id));
		return "ExistPersonPage";
	}
	@GetMapping("personEdit/{id}")
	public String editPerson(@PathVariable("id") Long id , Model model) {
		model.addAttribute("person", personService.getPersonByID(id));
		model.addAttribute("action", "person/person_edit/"+id.toString());
		return "PersonEditPage";
	}
	@PostMapping("/_editjob/{id}")
	public String edit(@PathVariable("id") Long id , @ModelAttribute("person") Person person){
		person.setId(id);
		personService.save(person);
		return "redirect:/person/personList";
	}
	@PostMapping("/_delete/{id}")
	public String delete(@PathVariable("id") Long id)
	{
		personService.delete(id);
		return "redirect:/person/personList";
	}
	@GetMapping("/PersonActionPage/{id}")
	public String personpage(@PathVariable("id") Long id , Model model) {
		model.addAttribute("persons", personService.getPersonByID(id));
		model.addAttribute("lifes",lifeService.getLifeByPersonId(id));
		model.addAttribute("vehicles",vehicleService.getVehiclebyPersonId(id));
		model.addAttribute("dasks",daskService.getDaskByPersonId(id));
		model.addAttribute("id",personService.getPersonByID(id).getId());
		return "PersonActionPage";
	}
}
