package com.springacentesbmdeneme.Service.concretes;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.PersonAgeJpa;
import com.springacentesbmdeneme.Service.abstracts.PersonAgeService;
import com.springacentesbmdeneme.entites.PersonAge;
import com.springacentesbmdeneme.entites.VehicleAgeRange;

@Service
public class PersonAgeManager implements PersonAgeService{
	@Autowired
	private PersonAgeJpa personAgeDao;
	@Override
	public List<PersonAge> getAll() {
		return this.personAgeDao.findAll();
	}
	@Override
	public void save(PersonAge personAge) {
		this.personAgeDao.save(personAge);
	}
	@Override
	public PersonAge choiceAgeRange(Date birth,Date date) {
		DateTime d1=new DateTime(date);
		DateTime birth1=new DateTime(birth);
		Interval interval=new Interval(birth1,d1);
		long year=interval.toDuration().getStandardDays()/365;
		for(PersonAge ageRange:this.personAgeDao.findAll()) {
			if(year>=ageRange.getMin()&&year<=ageRange.getMax()) {
				return ageRange;
			}
		}
			return null;
	}
}
