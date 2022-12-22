package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import com.springacentesbmdeneme.entites.Dask;
import com.springacentesbmdeneme.entites.Life;
import com.springacentesbmdeneme.entites.Proposal;

public interface DaskService {

	List<Dask> getAll();

	void save(Dask dask);
	
	Dask getDaskById(Long id);

	void daskInsuranceCreate(Dask dask);


	void delete(Long id);
	Proposal getDaskByProposalId(Long proposalId);
	List<Dask> getDaskByPersonId(Long id);
	void update(Dask dask);

	Proposal daskInsuranceCreateResponse(Dask dask);

	Long getPersonIdByDaskId(Long id);

}
