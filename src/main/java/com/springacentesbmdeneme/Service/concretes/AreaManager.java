package com.springacentesbmdeneme.Service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.AreaJpa;
import com.springacentesbmdeneme.Service.abstracts.AreaService;
import com.springacentesbmdeneme.entites.Area;
@Service
public class AreaManager implements AreaService{
	@Autowired
	private AreaJpa areaDao;
	
	@Override
	public List<Area> getAll() {
		return this.areaDao.findAll();
	}
	@Override
	public void save(Area area) {
		this.areaDao.save(area);
	}

}
