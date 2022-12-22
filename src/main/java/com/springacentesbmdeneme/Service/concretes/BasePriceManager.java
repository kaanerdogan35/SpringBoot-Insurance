package com.springacentesbmdeneme.Service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springacentesbmdeneme.Repository.abstracts.BasePriceJpa;

import com.springacentesbmdeneme.Service.abstracts.BasePriceService;
import com.springacentesbmdeneme.entites.BasePrice;


@Service
public class BasePriceManager implements BasePriceService{
	@Autowired
	private BasePriceJpa basePriceDao;
	@Override
	public void save(BasePrice basePrice) {
		this.basePriceDao.save(basePrice);
		
	}

	@Override
	public List<BasePrice> getAll() {
		
		return this.basePriceDao.findAll();
	}
	@Override
	public BasePrice getByType(String type) {
		return this.basePriceDao.findBasePriceByType(type);
	}
}
