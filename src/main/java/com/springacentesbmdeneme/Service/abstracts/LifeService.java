package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import com.springacentesbmdeneme.entites.Life;
import com.springacentesbmdeneme.entites.Proposal;

public interface LifeService {

	List<Life> getAll();
	void save(Life life);
	public void lifeInsuranceCreate(Life life);
	Life getLifeById(Long id);
	Life getLifeByProposalId(Long proposalId);
	void delete(Long id);
	List<Life> getLifeByPersonId(Long id);
	Proposal lifeInsuranceCreateResponse(Life life);
}
