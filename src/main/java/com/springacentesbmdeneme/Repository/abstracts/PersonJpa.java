package com.springacentesbmdeneme.Repository.abstracts;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springacentesbmdeneme.entites.Person;

@Repository
@Transactional
public interface PersonJpa extends JpaRepository<Person, Long>{
	@Query(value="select case when count(p.id)>0 then true else false end from person p where p.tc= :tc ",nativeQuery=true)
	public BigInteger personExistCheck(@Param("tc") Long tc);
	@Query(value="select * from person p where p.tc= :tc ", nativeQuery=true)
	public Person findPersonByTc(@Param("tc") Long tc);
}
