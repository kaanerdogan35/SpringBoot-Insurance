package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import com.springacentesbmdeneme.entites.Person;

public interface PersonService {
	void save(Person person);
	List<Person> getAll();
	String PersonExistCheck(Long tc);
	Person getPersonByTc(Long tc);
	Person getPersonByID(Long id);
	void delete(Long id);

}
