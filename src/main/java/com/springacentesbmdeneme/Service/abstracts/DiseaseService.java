package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import com.springacentesbmdeneme.entites.Disease;

public interface DiseaseService {

	List<Disease> getAll();

	void save(Disease disease);

}
