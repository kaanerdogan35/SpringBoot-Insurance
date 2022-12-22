package com.springacentesbmdeneme.Service.concretes;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.VehicleAgeRangeJpa;
import com.springacentesbmdeneme.Service.abstracts.VehicleAgeRangeService;
import com.springacentesbmdeneme.entites.VehicleAgeRange;
@Service
public class VehicleAgeRangeManager implements VehicleAgeRangeService{
	@Autowired
	private VehicleAgeRangeJpa vehicleAgeRangeDao;
	@Override
	public void save(VehicleAgeRange vehicleAgeRange) {
		this.vehicleAgeRangeDao.save(vehicleAgeRange);
	}
	@Override
	public List<VehicleAgeRange> getAll() {
		return this.vehicleAgeRangeDao.findAll();
	}
	@Override
	public VehicleAgeRange choiceAgeRange(int year,Date date) {
		DateTime d1=new DateTime(date);
		DateTime birth1=new DateTime(year,1,1,0,0);
		Interval interval=new Interval(birth1,d1);
		long age=interval.toDuration().getStandardDays()/365;
		for(VehicleAgeRange ageRange:this.vehicleAgeRangeDao.findAll()) {
			if(age>=ageRange.getMinAge()&&age<=ageRange.getMaxAge()) {
				return ageRange;
			}
		}
			return null;
	}
}
