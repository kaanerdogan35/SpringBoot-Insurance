package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import com.springacentesbmdeneme.entites.Career;

public interface CareerService {

	void save(Career career);

	List<Career> getAll();

}
