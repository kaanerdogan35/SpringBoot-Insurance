package com.springacentesbmdeneme.Repository.abstracts;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springacentesbmdeneme.entites.BuildAge;
@Repository
@Transactional
public interface BuildAgeJpa extends JpaRepository<BuildAge, Long>{

}
