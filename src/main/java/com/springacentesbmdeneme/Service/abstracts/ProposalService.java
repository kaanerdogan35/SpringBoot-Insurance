package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import com.springacentesbmdeneme.entites.Proposal;

public interface ProposalService {

	List<Long> getProposalsIdByVehicleId(Long id);

	boolean checkProposalStatusAcceptedorWaiting(List<Long> id);

	void save(Proposal proposal);

	void WaitingProposalsDecline(List<Proposal> proposals);
	List<Proposal> getAll();

	Proposal getProposalById(Long proposalId);

	void proposalAccept(Proposal proposal);

	void decline(Proposal proposal);

	void cancel(Proposal proposal);


	List<Long> getProposalsIdByDaskId(Long id);

}
