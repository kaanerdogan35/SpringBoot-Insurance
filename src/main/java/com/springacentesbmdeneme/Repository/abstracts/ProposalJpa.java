package com.springacentesbmdeneme.Repository.abstracts;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springacentesbmdeneme.entites.Proposal;
@Repository
@Transactional
public interface ProposalJpa extends JpaRepository<Proposal,Long>{
	@Query(value="select proposal_id from vehicle_proposal c where c.vehicle_id=:id",nativeQuery = true)
	public List<Long> findProposalsByVehicleId(@Param("id")Long id);
	@Query(value="select case when p.status = 'accepted'or p.status is NULL then false else true end from proposal p where p.id=:id",nativeQuery = true)
	public BigInteger checkAcceptedorWaiting(@Param("id")Long id);
	@Query(value="select proposal_id from daskproposal d where d.dask_id=:id",nativeQuery = true)
	public List<Long> findProposalsByDaskId(@Param("id")Long id);


}
