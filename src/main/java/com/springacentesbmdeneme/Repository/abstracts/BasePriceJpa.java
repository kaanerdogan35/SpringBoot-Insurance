package com.springacentesbmdeneme.Repository.abstracts;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springacentesbmdeneme.entites.BasePrice;
@Repository
@Transactional
public interface BasePriceJpa extends JpaRepository<BasePrice, Integer>{
	@Query(value="select * from baseprice b where b.type=:type",nativeQuery = true)
	public BasePrice findBasePriceByType(@Param("type")String type);
}
