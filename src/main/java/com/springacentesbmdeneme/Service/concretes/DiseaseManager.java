package com.springacentesbmdeneme.Service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.DiseaseJpa;
import com.springacentesbmdeneme.Service.abstracts.DiseaseService;
import com.springacentesbmdeneme.entites.Disease;



@Service
public class DiseaseManager implements DiseaseService{
	@Autowired
	private DiseaseJpa diseaseDao;
	@Override
	public void save(Disease disease) {
		this.diseaseDao.save(disease);
		
	}
	@Override
	public List<Disease> getAll() {
		
		return this.diseaseDao.findAll();
	}
	
		 
}
