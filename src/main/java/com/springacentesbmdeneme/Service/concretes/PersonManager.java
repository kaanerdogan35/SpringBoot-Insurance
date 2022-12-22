package com.springacentesbmdeneme.Service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.PersonJpa;
import com.springacentesbmdeneme.Service.abstracts.PersonService;
import com.springacentesbmdeneme.entites.Person;
@Service
public class PersonManager implements PersonService {
	@Autowired 
	private PersonJpa personDao;

	@Override
	public void save(Person person) {
		personDao.save(person);
		
	}

	@Override
	public List<Person> getAll() {
		return this.personDao.findAll();
	}
	@Override
	public String PersonExistCheck(Long tc) {
		
		return personDao.personExistCheck(tc).toString();
	}

	@Override
	public Person getPersonByTc(Long tc) {
		
		return this.personDao.findPersonByTc(tc);
	}

	@Override
	public Person getPersonByID(Long Id) {
		
		return this.personDao.findById(Id).get();
	}

	@Override
	public void delete(Long id) {
		
		this.personDao.deleteById(id);
	}
}
