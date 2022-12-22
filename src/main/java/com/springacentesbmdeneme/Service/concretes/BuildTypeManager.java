package com.springacentesbmdeneme.Service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.springacentesbmdeneme.Repository.abstracts.BuildTypeJpa;
import com.springacentesbmdeneme.Service.abstracts.BuildTypeService;
import com.springacentesbmdeneme.entites.BuildAge;
import com.springacentesbmdeneme.entites.BuildType;
@Service
public class BuildTypeManager implements BuildTypeService {
	@Autowired
	private BuildTypeJpa buildTypeDao;
	@Override
	public void save(BuildType buildType) {
		this.buildTypeDao.save(buildType);
		
	}

	@Override
	public List<BuildType> getAll() {
		// TODO Auto-generated method stub
		return this.buildTypeDao.findAll();
	}
}
