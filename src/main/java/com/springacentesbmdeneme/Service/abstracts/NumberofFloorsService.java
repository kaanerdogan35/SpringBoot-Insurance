package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import com.springacentesbmdeneme.entites.NumberofFloors;

public interface NumberofFloorsService {

	void save(NumberofFloors floor);

	List<NumberofFloors> getAll();

}
