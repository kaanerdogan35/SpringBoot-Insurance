package com.springacentesbmdeneme.Service.abstracts;

import java.util.Date;
import java.util.List;

import com.springacentesbmdeneme.entites.BuildAge;

public interface BuildAgeService {

	void save(BuildAge buildAge);

	List<BuildAge> getAll();

}
