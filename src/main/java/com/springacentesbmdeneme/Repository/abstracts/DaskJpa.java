package com.springacentesbmdeneme.Repository.abstracts;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springacentesbmdeneme.entites.Dask;
import com.springacentesbmdeneme.entites.Life;
@Repository
@Transactional
public interface DaskJpa  extends JpaRepository<Dask, Long>{
	@Query(value="select dask_id from daskproposal d where d.proposal_id=:id",nativeQuery = true)
	public Long findDaskIdByProposalId(@Param("id")Long id);
	@Modifying
	@Query(value="update dask set adress=:adress,numberoffloor_id=:numberoffloor_id,buildage_id=:buildage_id,area_id=:area_id where id=:id",nativeQuery= true)
	public void updateDaskInfo(@Param("id")Long id, @Param("adress")String adress,@Param("numberoffloor_id")Long numberoffloor_id,@Param("buildage_id")Long buildage_id,@Param("area_id")Long area_id);
	@Query(value="select * from dask l where l.person_id= :id ", nativeQuery=true)
	public List<Dask> findDaskByPersonId(@Param("id") Long id);
	@Query(value="select person_id from dask v where v.id=:id",nativeQuery = true)
	public BigInteger findPersonIdByDaskID(@Param("id") Long id);
}
