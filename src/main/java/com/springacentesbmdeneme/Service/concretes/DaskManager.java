package com.springacentesbmdeneme.Service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.DaskJpa;
import com.springacentesbmdeneme.Service.abstracts.BasePriceService;
import com.springacentesbmdeneme.Service.abstracts.DaskService;
import com.springacentesbmdeneme.Service.abstracts.ProposalService;
import com.springacentesbmdeneme.entites.Dask;
import com.springacentesbmdeneme.entites.Proposal;
import com.springacentesbmdeneme.entites.Vehicle;
@Service

public class DaskManager implements DaskService{
	@Autowired
	private DaskJpa daskDao;
	@Autowired
	private ProposalService proposalService;
	@Autowired
	private BasePriceService basePriceService;
	@Override
	public void save(Dask dask) {
		this.daskDao.save(dask);
		
	}

	@Override
	public List<Dask> getAll() {
		return this.daskDao.findAll();
	}

	@Override
	public Dask getDaskById(Long id) {
		return this.daskDao.findById(id).get();
	}
	@Override
	public void daskInsuranceCreate(Dask dask) {
		List<Proposal> proposalList=dask.getDaskproposal();
		proposalList.add(daskInsuranceCreateResponse(dask));
		dask.setDaskproposal(proposalList);;
		daskDao.save(dask);
	}
	@Override
	public Proposal daskInsuranceCreateResponse(Dask dask) {
			Proposal proposal=new Proposal();
			proposal.setOrder_date(new java.util.Date());
			double priceCalculator=basePriceService.getByType("Dask").getInitialprice();
			priceCalculator*=dask.getBuild_age().getPrice_multiplier();
			priceCalculator*=dask.getBuildtype().getPrice_multiplier();
			priceCalculator*=dask.getCity().getPrice_multiplier();
			priceCalculator*=dask.getFloor().getPrice_multiplier();
			priceCalculator = Math.round(priceCalculator*100.0)/100.0;
			proposal.setPrice(priceCalculator);
			proposalService.save(proposal);
			proposalService.WaitingProposalsDecline(dask.getDaskproposal());
			return proposal;
	}
	@Override
	public void delete(Long id) {
		this.daskDao.deleteById(id);;
	}
	@Override
	public void update(Dask dask) {
		this.daskDao.updateDaskInfo(dask.getId(),dask.getAdress(), dask.getFloor().getId(), dask.getBuild_age().getId(), dask.getArea().getId());
	}
	@Override
	public Proposal getDaskByProposalId(Long id) {
		return this.proposalService.getProposalById(daskDao.findDaskIdByProposalId(id));
	}

	@Override
	public List<Dask> getDaskByPersonId(Long id) {
		// TODO Auto-generated method stub
		return this.daskDao.findDaskByPersonId(id);
	}
	@Override
	public Long getPersonIdByDaskId(Long id) {
		// TODO Auto-generated method stub
		return Long.valueOf(daskDao.findPersonIdByDaskID(id).toString());
	}
	
}
