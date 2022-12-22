package com.springacentesbmdeneme.Service.abstracts;

import java.util.Date;
import java.util.List;

import com.springacentesbmdeneme.entites.PersonAge;

public interface PersonAgeService {

	List<PersonAge> getAll();

	void save(PersonAge personAge);


	PersonAge choiceAgeRange(Date birth, Date date);

}
