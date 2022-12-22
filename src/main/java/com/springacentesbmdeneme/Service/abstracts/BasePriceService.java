package com.springacentesbmdeneme.Service.abstracts;

import java.util.List;

import com.springacentesbmdeneme.entites.BasePrice;

public interface BasePriceService {

	void save(BasePrice basePrice);

	List<BasePrice> getAll();
	BasePrice getByType(String type);
	

}
