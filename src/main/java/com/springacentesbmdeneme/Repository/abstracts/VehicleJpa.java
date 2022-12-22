package com.springacentesbmdeneme.Repository.abstracts;

import java.math.BigInteger;
import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springacentesbmdeneme.entites.Vehicle;
@Repository
@Transactional
public interface VehicleJpa extends JpaRepository<Vehicle,Long>{
	@Query(value="select case when v.proposal_id IS NOT NULL then true else false end from vehicleProposal v where v.vehicle_id= :id ",nativeQuery=true)
	public BigInteger vehicleProposalExist(@Param("id") Long id);
	@Query(value="select vehicle_id from vehicle_proposal v where v.proposal_id=:id",nativeQuery = true)
	public Long findVehicleIdByProposalId(@Param("id")Long id);
	@Query(value="select * from vehicle v where v.person_id= :id ", nativeQuery=true)
	public List<Vehicle> findVehiclebyPersonId(@Param("id") Long id);
	@Query(value="select person_id from vehicle v where v.id=:id",nativeQuery = true)
	public BigInteger findPersonIdByVehicleID(@Param("id") Long id);


}
