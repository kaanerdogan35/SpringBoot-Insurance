package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import com.springacentesbmdeneme.entites.BuildType;

public interface BuildTypeService {

	List<BuildType> getAll();

	void save(BuildType buildType);

}
