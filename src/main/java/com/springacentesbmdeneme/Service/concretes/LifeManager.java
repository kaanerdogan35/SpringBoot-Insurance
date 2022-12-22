package com.springacentesbmdeneme.Service.concretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.LifeJpa;
import com.springacentesbmdeneme.Service.abstracts.BasePriceService;
import com.springacentesbmdeneme.Service.abstracts.LifeService;
import com.springacentesbmdeneme.Service.abstracts.PersonAgeService;
import com.springacentesbmdeneme.Service.abstracts.PersonService;
import com.springacentesbmdeneme.Service.abstracts.ProposalService;
import com.springacentesbmdeneme.entites.Life;
import com.springacentesbmdeneme.entites.PersonAge;
import com.springacentesbmdeneme.entites.Proposal;
import com.springacentesbmdeneme.entites.Vehicle;



@Service
public class LifeManager implements LifeService {
	@Autowired
	private LifeJpa lifeDao;
	@Autowired
	private ProposalService proposalService;
	@Autowired 
	private PersonService personService;
	@Autowired
	private PersonAgeService personAgeService;
	@Autowired
	private BasePriceService basePriceService;
	@Override
	public List<Life> getAll() {
		return lifeDao.findAll();
	}
	@Override
	public void save(Life life) {
		this.lifeDao.save(life);	
	}
	@Override
	public void lifeInsuranceCreate(Life life) {
		life.setProposal(lifeInsuranceCreateResponse(life));;
		lifeDao.save(life);
	}
	@Override
	public Proposal lifeInsuranceCreateResponse(Life life) {
		Proposal proposal=new Proposal();
		proposal.setOrder_date(new Date());
		PersonAge personAge=personAgeService.choiceAgeRange(life.getPerson().getBirth_date(), new Date());
		life.setPerson_age(personAge);
		double priceCalculator=basePriceService.getByType("Life").getInitialprice();
		priceCalculator*=life.getCareer().getPrice_multiplier();
		priceCalculator*=life.getDisease().getPrice_multiplier();
		priceCalculator*=life.getPerson_age().getPrice_multiplier();
		priceCalculator=life.getSecurity_deposit()/priceCalculator;
		priceCalculator = Math.round(priceCalculator*100.0)/100.0;
		proposal.setPrice(priceCalculator);
		proposalService.save(proposal);
		return proposal;
	}
	@Override
	public Life getLifeById(Long id) {
		return this.lifeDao.findById(id).get();
	}
	@Override
	public Life getLifeByProposalId(Long id) {
		return this.lifeDao.findById(this.lifeDao.findLifeByProposalId(id)).get();
	}
	@Override
	public void delete(Long id) {
		this.lifeDao.deleteById(id);
		
	}
	@Override
	public List<Life> getLifeByPersonId(Long id) {
		return this.lifeDao.findLifeByPersonId(id);
	}
	
	
}
