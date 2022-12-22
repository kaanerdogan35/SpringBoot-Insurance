package com.springacentesbmdeneme.Repository.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springacentesbmdeneme.entites.Life;
import com.springacentesbmdeneme.entites.Proposal;
import com.springacentesbmdeneme.entites.Vehicle;

@Repository
@Transactional
public interface LifeJpa extends JpaRepository<Life, Long> {
	@Query(value="select id from life l where l.proposal_id=:id",nativeQuery = true)
	public Long findLifeByProposalId(@Param("id")Long id);
	@Query(value="select * from life l where l.person_id= :id ", nativeQuery=true)
	public List<Life> findLifeByPersonId(@Param("id") Long id);
	
}
